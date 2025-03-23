package banduty.bsroleplay.mixin;

import banduty.bsroleplay.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "teleportTo", at = @At("HEAD"), cancellable = true)
    public void teleport(TeleportTarget teleportTarget, CallbackInfoReturnable<Entity> cir) {
        ServerPlayerEntity playerEntity = (ServerPlayerEntity) (Object) this;

        if (playerEntity.getWorld() == null ||
                !((IEntityDataSaver) playerEntity).bsroleplay$getPersistentData().getBoolean("handcuffed")) return;

        cir.cancel();
    }
}
