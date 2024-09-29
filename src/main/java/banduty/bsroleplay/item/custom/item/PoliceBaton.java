
package banduty.bsroleplay.item.custom.item;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.datacomponents.ModDataComponents;
import banduty.bsroleplay.item.client.items.PoliceBatonModel;
import banduty.bsroleplay.util.IEntityDataSaver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import java.util.List;
import java.util.function.Consumer;

public class PoliceBaton extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public PoliceBaton(Settings settings) {
        super(settings);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoItemRenderer<PoliceBaton> renderer;

            @Override
            public GeoItemRenderer<PoliceBaton> getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new GeoItemRenderer<>(new PoliceBatonModel());

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<PoliceBaton> animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static void writeBlockPosToNbt(ItemStack stack, BlockPos blockPos) {
        stack.set(ModDataComponents.BLOCKPOS, blockPos);
    }

    @Nullable
    public static BlockPos readBlockPosFromNbt(ItemStack stack) {
        if (stack.getComponents().get(ModDataComponents.BLOCKPOS) == null) {
            return null;
        }
        return stack.getComponents().get(ModDataComponents.BLOCKPOS);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        PoliceBaton.writeBlockPosToNbt(context.getStack(), blockPos);
        if (playerEntity != null) playerEntity.sendMessage(Text.literal("Prison set: " + "§4x:" + "§r" + blockPos.getX() +
                " §4y:" + "§r" + blockPos.getY() + " §4z:" + "§r" + blockPos.getZ()).fillStyle(Style.EMPTY), true);
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof PlayerEntity playerAttacker)) {
            return false;
        }
        if (!(target instanceof PlayerEntity playerTarget)) {
            return false;
        }
        if (BsRolePlay.CONFIG.common.modifyPoliceBatonSlowness
                && !playerAttacker.getItemCooldownManager().isCoolingDown(this)
                && !((IEntityDataSaver) playerTarget).bsroleplay$getPersistentData().getBoolean("handcuffed")) {
            playerAttacker.getItemCooldownManager().set(this, BsRolePlay.CONFIG.common.getPoliceBatonCooldown() * 20);
            playerTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, BsRolePlay.CONFIG.common.getPoliceBatonSlownessDuration() * 20,
                    BsRolePlay.CONFIG.common.getPoliceBatonSlownessLevel() - 1));
            return true;
        }
        if (((IEntityDataSaver) playerTarget).bsroleplay$getPersistentData().getBoolean("handcuffed") && !playerAttacker.getItemCooldownManager().isCoolingDown(this) &&
                stack.getComponents().get(ModDataComponents.BLOCKPOS) != null) {
            BlockPos blockPos = PoliceBaton.readBlockPosFromNbt(stack);
            if (blockPos != null) playerTarget.teleport(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), true);
            for (PlayerEntity players : attacker.getWorld().getPlayers()) {
                players.sendMessage(Text.literal("§f" + playerTarget.getGameProfile().getName() + "§r" + " has been prisoned")
                        .fillStyle(Style.EMPTY.withColor(Formatting.RED)), true);
            }
            playerAttacker.getItemCooldownManager().set(this, 300);
            playerTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 10));
            return true;
        }

        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (BsRolePlay.CONFIG.common.showItemTooltips) {
            tooltip.add(Text.translatable("tooltip.bsroleplay.policebaton_slowness.tooltip"));
            tooltip.add(Text.translatable("tooltip.bsroleplay.policebaton_save_jail.tooltip"));
            tooltip.add(Text.translatable("tooltip.bsroleplay.policebaton_jail.tooltip"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}