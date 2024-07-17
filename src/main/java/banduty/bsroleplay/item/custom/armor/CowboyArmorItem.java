package banduty.bsroleplay.item.custom.armor;

import banduty.bsroleplay.item.client.armor.CowboyRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import java.util.function.Consumer;

public class CowboyArmorItem extends ArmorItem implements GeoItem {
    public CowboyArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            boolean hasCorrectHelmet = player.getInventory().getArmorStack(3).getItem() instanceof ArmorItem helmetItem &&
                    helmetItem.getMaterial() == this.material;
            boolean hasCorrectChestplate = player.getInventory().getArmorStack(2).getItem() instanceof ArmorItem chestplateItem &&
                    chestplateItem.getMaterial() == this.material;

            if (hasCorrectHelmet && hasCorrectChestplate && player.getVehicle() instanceof HorseEntity horseEntity) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 0,
                        false, true, false));
                horseEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 0,
                        false, true, false));
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private CowboyRenderer renderer;

            @Override
            public @Nullable <T extends LivingEntity> BipedEntityModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack,
                                                                                              @Nullable EquipmentSlot equipmentSlot,
                                                                                              @Nullable BipedEntityModel<T> original) {
                if (this.renderer == null)
                    this.renderer = new CowboyRenderer();

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller", 0, this::predicate));

    }

    private PlayState predicate(AnimationState<CowboyArmorItem> animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}