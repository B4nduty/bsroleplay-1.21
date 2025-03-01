package banduty.bsroleplay.event;

import banduty.bsroleplay.item.custom.item.PoliceBaton;
import banduty.bsroleplay.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class LivingEntityDamageHandler implements ServerLivingEntityEvents.AllowDamage{
    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if (entity instanceof PlayerEntity playerEntity) {
            if (source.getAttacker() instanceof PlayerEntity attacker && attacker.getMainHandStack().getItem() instanceof PoliceBaton) return true;
            return ((IEntityDataSaver) playerEntity).bsroleplay$getPersistentData().getBoolean("handcuffed");
        }
        return true;
    }
}
