package banduty.bsroleplay.mixin;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.util.IEntityDataSaver;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onChatMessage", at = @At("HEAD"), cancellable = true)
    public void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        if (((IEntityDataSaver) player).bsroleplay$getPersistentData().getBoolean("handcuffed") && BsRolePlay.CONFIG.common.getHandcuffsChat() == 2) {
            player.sendMessage(Text.literal("You cannot send chat messages while being Handcuffed!"), false);
            ci.cancel();
        }
    }

    @Inject(method = "executeCommand", at = @At("HEAD"), cancellable = true)
    public void onExecuteCommand(String command, CallbackInfo ci) {
        if (((IEntityDataSaver) player).bsroleplay$getPersistentData().getBoolean("handcuffed") && BsRolePlay.CONFIG.common.getHandcuffsChat() >= 1) {
            player.sendMessage(Text.literal("You cannot use commands while being Handcuffed!"), false);
            ci.cancel();
        }
    }
}