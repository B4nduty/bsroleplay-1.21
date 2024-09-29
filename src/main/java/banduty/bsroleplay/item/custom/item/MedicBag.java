
package banduty.bsroleplay.item.custom.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class MedicBag extends Item {

    public MedicBag(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        Vec3d playerPos = user.getPos();
        double maxRadius = 3.0;
        int particlesPerCircle = 50;
        int animationFrames = 4;

        for (int frame = 0; frame < animationFrames; frame++) {
            double progress = (double) frame / animationFrames;

            for (int i = 0; i < particlesPerCircle; i++) {
                double angle = (2 * Math.PI / particlesPerCircle) * i;

                double offsetX = Math.cos(angle) * maxRadius * progress;
                double offsetZ = Math.sin(angle) * maxRadius * progress;

                double particleX = playerPos.getX() + offsetX;
                double particleY = playerPos.getY() + 0.1;
                double particleZ = playerPos.getZ() + offsetZ;

                int color = 5736704;
                float red = ColorHelper.Argb.getRed(color) / 255.0F;
                float green = ColorHelper.Argb.getGreen(color) / 255.0F;
                float blue = ColorHelper.Argb.getBlue(color) / 255.0F;

                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(new DustParticleEffect(new Vector3f(red, green, blue), 1.0f),
                            particleX,
                            particleY,
                            particleZ,
                            1, 0.0, 0.0, 0.0, 0.0);
                }
            }
        }
        if (!world.isClient() && user instanceof PlayerEntity player) {
            if (!player.isCreative()) itemStack.decrement(1);
            Box detectionBox = new Box(player.getBlockPos()).expand(3);

            world.getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                            entity != player && player.getBlockPos().isWithinDistance(entity.getBlockPos(), 4)
                                    && entity instanceof PlayerEntity)
                    .forEach(entity -> entity.setHealth(Math.min(entity.getHealth() + 16.0f, 20.0f)));

            player.getItemCooldownManager().set(this, 90 * 20);
        }

        return TypedActionResult.success(itemStack);
    }
}