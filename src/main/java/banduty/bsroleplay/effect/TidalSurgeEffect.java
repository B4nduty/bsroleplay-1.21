package banduty.bsroleplay.effect;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.networking.packet.VelocityUpdateS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class TidalSurgeEffect extends StatusEffect {
    private static final Identifier TIDAL_SURGE_STRENGTH_REDUCTION = BsRolePlay.identifierOf("tidal_surge_strength_reduction");
    private static final double STRENGTH_REDUCTION_AMOUNT = -2.0;

    protected TidalSurgeEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient) {
            double velocityX = entity.getVelocity().x * 0.4;
            double velocityY = entity.getVelocity().y * 0.4;
            double velocityZ = entity.getVelocity().z * 0.4;
            entity.setVelocity(velocityX, velocityY, velocityZ);

            if (entity instanceof PlayerEntity player) {
                ServerPlayNetworking.send((ServerPlayerEntity) player, new VelocityUpdateS2CPacket(player.getId(), velocityX, velocityY, velocityZ));
            }

            EntityAttributeInstance attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            if (attributeInstance != null) {
                EntityAttributeModifier modifier = new EntityAttributeModifier(
                        TIDAL_SURGE_STRENGTH_REDUCTION,
                        STRENGTH_REDUCTION_AMOUNT,
                        EntityAttributeModifier.Operation.ADD_VALUE
                );
                if (!attributeInstance.hasModifier(modifier.id())) {
                    attributeInstance.addTemporaryModifier(modifier);
                }
            }
        }
        super.applyUpdateEffect(entity, amplifier);
        return true;
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        EntityAttributeInstance attackDamage = attributeContainer.getCustomInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (attackDamage != null) {
            attackDamage.removeModifier(TIDAL_SURGE_STRENGTH_REDUCTION);
        }
        super.onRemoved(attributeContainer);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
