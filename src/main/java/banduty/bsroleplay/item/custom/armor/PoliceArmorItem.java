
package banduty.bsroleplay.item.custom.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.client.armor.PoliceArmorRenderer;
import banduty.bsroleplay.util.IEntityDataSaver;
import banduty.bsroleplay.util.StaminaData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
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
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class PoliceArmorItem extends ArmorItem implements GeoItem {
    public PoliceArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player && BsRolePlay.CONFIG.common.modifyPoliceSetEffects) {
            boolean wearingCorrectArmor = true;
            for (ItemStack armorStack : player.getArmorItems()) {
                if (!(armorStack.getItem() instanceof ArmorItem armorItem) || armorItem.getMaterial() != this.material) {
                    wearingCorrectArmor = false;
                    break;
                }
            }
            if (wearingCorrectArmor) {
                StaminaData.setStamina((IEntityDataSaver) player, true);
                if (player.age % 10 == 0) StaminaData.removeStamina((IEntityDataSaver) player, 0);

                super.inventoryTick(stack, world, entity, slot, selected);
                return;
            }
            StaminaData.setStamina((IEntityDataSaver) player, false);
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private PoliceArmorRenderer renderer;

            @Override
            public <T extends LivingEntity> BipedEntityModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack,
                                                                                              @Nullable EquipmentSlot equipmentSlot,
                                                                                              @Nullable BipedEntityModel<T> original) {
                if (this.renderer == null)
                    this.renderer = new PoliceArmorRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller", 0, this::predicate));

    }

    private PlayState predicate(AnimationState<PoliceArmorItem> animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
