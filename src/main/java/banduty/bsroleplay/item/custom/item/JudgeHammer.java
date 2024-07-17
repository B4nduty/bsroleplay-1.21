
package banduty.bsroleplay.item.custom.item;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.client.items.JudgeHammerModel;
import banduty.bsroleplay.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import java.util.List;
import java.util.function.Consumer;

public class JudgeHammer extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public JudgeHammer(Settings settings) {
        super(settings);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoItemRenderer<JudgeHammer> renderer;

            @Override
            public GeoItemRenderer<JudgeHammer> getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new GeoItemRenderer<>(new JudgeHammerModel());

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<JudgeHammer> animationState) {
        if (MinecraftClient.getInstance().player == null) return PlayState.STOP;
        Item mainHand = MinecraftClient.getInstance().player.getMainHandStack().getItem();
        Item leftHand = MinecraftClient.getInstance().player.getOffHandStack().getItem();
        boolean coolDown = MinecraftClient.getInstance().player.getItemCooldownManager().isCoolingDown(this);
        if (BsRolePlay.CONFIG.common.modifyJudgeHammerSound && (mainHand == this || leftHand == this)) {
            if (coolDown) {
                animationState.getController().setAnimation(RawAnimation.begin().then("unable", Animation.LoopType.HOLD_ON_LAST_FRAME));
                return PlayState.CONTINUE;
            }
            animationState.getController().setAnimation(RawAnimation.begin().then("able", Animation.LoopType.HOLD_ON_LAST_FRAME));
            return PlayState.CONTINUE;
        }
        animationState.getController().setAnimation(RawAnimation.begin().then("unable", Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        if (BsRolePlay.CONFIG.common.modifyJudgeHammerSound) {
            BlockPos blockPos = context.getBlockPos();
            PlayerEntity player = context.getPlayer();

            context.getWorld().playSound(null, blockPos, ModSounds.JUDGE_HAMMER_RIGHT_CLICK,
                    SoundCategory.BLOCKS, 1f, 1f);

            if (player != null) {
                player.getItemCooldownManager().set(this, BsRolePlay.CONFIG.common.getJudgeHammerCooldown() * 20);
            }

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (BsRolePlay.CONFIG.common.showItemTooltips) {
            tooltip.add(Text.translatable("tooltip.bsroleplay.judgehammer.tooltip"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
