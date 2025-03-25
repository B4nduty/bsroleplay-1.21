
package banduty.bsroleplay.screen.clockpunch;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.networking.packet.UpdateCurrencyCounterPacketC2SPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Environment(EnvType.CLIENT)
public class ClockPunchScreen extends HandledScreen<ClockPunchScreenHandler> {
    private static final Identifier TEXTURE = BsRolePlay.identifierOf("textures/gui/clockpunch_gui.png");
    private TextFieldWidget salaryCounter;
    private String finishTimeString;
    private long timeToNextPayment = 180 * 60 * 60 * 1000;

    public ClockPunchScreen(ClockPunchScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 247;
        this.backgroundHeight = 190;
        this.playerInventoryTitleY = 1000;
        this.titleY = 1000;
        updateFinishTime();
    }

    @Override
    protected void init() {
        super.init();

        this.salaryCounter = new TextFieldWidget(
                this.textRenderer,
                this.x + 88,
                this.y + 61,
                68,
                19,
                Text.literal("")
        );

        this.salaryCounter.setText(this.handler.getSalaryCounter() + " RP");

        this.addSelectableChild(this.salaryCounter);

        this.setInitialFocus(this.salaryCounter);

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Done"),
                button -> this.onDoneButtonClicked()
        ).dimensions(
                this.x + 102,
                this.y + 83,
                40,
                18
        ).build());
    }

    private void onDoneButtonClicked() {
        if (this.salaryCounter == null || this.handler == null || this.handler.blockEntity == null) {
            return;
        }

        String inputText = this.salaryCounter.getText();

        String cleanedInput = inputText.replaceAll("[^0-9]", "");

        if (cleanedInput.isEmpty()) {
            this.salaryCounter.setText(this.handler.getSalaryCounter() + " RP");
            this.salaryCounter.setFocused(true);
            return;
        }

        int newValue = Integer.parseInt(cleanedInput);

        if (newValue < 0) {
            this.close();
            return;
        }

        this.handler.setSalaryCounter(newValue);
        this.sendSalaryUpdatePacket(newValue);
        this.close();
    }

    private void sendSalaryUpdatePacket(int amount) {
        ClientPlayNetworking.send(new UpdateCurrencyCounterPacketC2SPacket(this.handler.syncId, amount));
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        this.salaryCounter.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal(this.handler.getCoinsCounter() + " RP"), this.x + 205, this.y + 12, 0xffffff);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal(finishTimeString), this.x + 96, this.y + 143, 0xffffff);

        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.salaryCounter.charTyped(chr, modifiers)) {
            return true;
        }
        return super.charTyped(chr, modifiers);
    }

    @Override
    protected void handledScreenTick() {
        super.handledScreenTick();
        updateFinishTime();
    }

    private void updateFinishTime() {
        long currentTime = System.currentTimeMillis();
        long finishTime = currentTime + timeToNextPayment;

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        finishTimeString = sdf.format(new Date(finishTime));
    }
}
