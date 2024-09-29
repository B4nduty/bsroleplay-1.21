
package banduty.bsroleplay.screen.strongbox;

import banduty.bsroleplay.BsRolePlay;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class StrongboxScreen extends HandledScreen<StrongboxScreenHandler> {
    private static final Identifier TEXTURE = BsRolePlay.identifierOf("textures/gui/strongbox_gui.png");

    public StrongboxScreen(StrongboxScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 194;
        this.backgroundHeight = 165;
        this.playerInventoryTitleY = 1000;
        this.titleY = 1000;
    }

    @Override
    protected void init() {
        super.init();
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
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Strongbox"), this.x + 87, this.y + 10, 0xffffff);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
