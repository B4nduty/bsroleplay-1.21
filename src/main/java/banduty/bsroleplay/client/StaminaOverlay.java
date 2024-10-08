package banduty.bsroleplay.client;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.mixin.ClientAdvancementManagerAccessor;
import banduty.bsroleplay.util.IEntityDataSaver;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientAdvancementManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

import java.util.Map;

public class StaminaOverlay implements HudRenderCallback {
    private static final Identifier STAMINA = BsRolePlay.identifierOf("textures/overlay/stamina.png");
    private static final Identifier BLOCKED_STAMINA = BsRolePlay.identifierOf("textures/overlay/blocked_stamina.png");
    private static final Identifier NETHER_ADVANCEMENT = Identifier.ofVanilla("nether/root");
    private static final Identifier DRAGON_ADVANCEMENT = Identifier.ofVanilla("end/kill_dragon");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        int x = 0;
        int y = 0;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        boolean shouldStamina = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getBoolean("stamina_boolean");
        boolean blockedStamina = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getBoolean("blocked_stamina");
        int stamina = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getInt("stamina_int");
        if (shouldStamina && !player.isSpectator()) {
            ClientPlayNetworkHandler networkHandler = player.networkHandler;
            if (networkHandler == null) return;

            ClientAdvancementManager advancementManager = networkHandler.getAdvancementHandler();
            if (advancementManager == null) return;

            int totalStamina = 60;

            AdvancementEntry netherAdvancement = advancementManager.get(NETHER_ADVANCEMENT);
            AdvancementEntry dragonAdvancement = advancementManager.get(DRAGON_ADVANCEMENT);

            if (netherAdvancement != null || dragonAdvancement != null) {
                AdvancementProgress netherProgress = getAdvancementProgress(advancementManager, netherAdvancement);
                AdvancementProgress dragonProgress = getAdvancementProgress(advancementManager, dragonAdvancement);

                if (dragonAdvancement != null && dragonProgress.isDone()) {
                    totalStamina = 180;
                } else if (netherAdvancement != null && netherProgress.isDone()) {
                    totalStamina = 120;
                }
            }
            double constantWidth = (34.0/(double) totalStamina);
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null) {
                int width = client.getWindow().getScaledWidth();
                int height = client.getWindow().getScaledHeight();

                x = width / 2;
                y = height;
            }

            for(int i = 0; i < totalStamina; i++) {
                if(stamina > i) {
                    if (blockedStamina) {
                        RenderSystem.setShaderTexture(0, BLOCKED_STAMINA);
                        drawContext.drawTexture(BLOCKED_STAMINA, x + 94, y - 31, 0, 0, (int) (i * constantWidth) + 1, 34, 34, 34);
                    } else {
                        RenderSystem.setShaderTexture(0, STAMINA);
                        drawContext.drawTexture(STAMINA, x + 94, y - 31, 0, 0, (int) (i * constantWidth) + 1, 34, 34, 34);
                    }
                } else {
                    break;
                }
            }
        }
    }

    private AdvancementProgress getAdvancementProgress(ClientAdvancementManager manager, AdvancementEntry entry) {
        ClientAdvancementManagerAccessor accessor = (ClientAdvancementManagerAccessor) manager;
        Map<AdvancementEntry, AdvancementProgress> progressMap = accessor.getAdvancementProgresses();
        return progressMap.get(entry);
    }
}