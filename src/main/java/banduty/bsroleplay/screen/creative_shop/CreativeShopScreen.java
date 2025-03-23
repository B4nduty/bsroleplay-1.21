
package banduty.bsroleplay.screen.creative_shop;

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

@Environment(EnvType.CLIENT)
public class CreativeShopScreen extends HandledScreen<CreativeShopScreenHandler> {
    private static final Identifier TEXTURE = BsRolePlay.identifierOf("textures/gui/creative_shop_gui.png");
    private TextFieldWidget currencyTextField;

    public CreativeShopScreen(CreativeShopScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 194;
        this.backgroundHeight = 165;
        this.playerInventoryTitleY = 1000;
        this.titleY = 1000;
    }

    @Override
    protected void init() {
        super.init();

        this.currencyTextField = new TextFieldWidget(
                this.textRenderer,
                this.x + 52,
                this.y + 34,
                72,
                18,
                Text.literal("")
        );

        this.currencyTextField.setText(this.handler.getCurrencyCounter() + " RP");

        this.addSelectableChild(this.currencyTextField);

        this.setInitialFocus(this.currencyTextField);

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Done"),
                button -> this.onDoneButtonClicked()
        ).dimensions(
                this.x + 68,
                this.y + 59,
                40,
                18
        ).build());
    }

    private void onDoneButtonClicked() {
        String inputText = this.currencyTextField.getText();

        String cleanedInput = inputText.replaceAll("[^0-9]", "");

        int newValue = cleanedInput.isEmpty() ? -1 : Integer.parseInt(cleanedInput);
        if (newValue < 0) {
            this.close();
            return;
        }

        this.handler.setCurrencyCounter(newValue);
        this.sendCurrencyUpdatePacket(newValue);
        this.close();
    }

    private void sendCurrencyUpdatePacket(int amount) {
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

        this.currencyTextField.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Creative Shop"), this.x + 88, this.y + 15, 0xffffff);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.currencyTextField.charTyped(chr, modifiers)) {
            return true;
        }
        return super.charTyped(chr, modifiers);
    }
}
