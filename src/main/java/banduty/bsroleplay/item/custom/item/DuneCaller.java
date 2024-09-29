
package banduty.bsroleplay.item.custom.item;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.datacomponents.ModDataComponents;
import banduty.bsroleplay.entity.custom.SandstormProjectileEntity;
import banduty.bsroleplay.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class DuneCaller extends Item {
    private static final int ITEM_BAR_COLOR = 0x006666ff;
    public DuneCaller(Settings settings) {
        super(settings);
    }

    public static int getChargesFromNbt(ItemStack itemStack) {
        if (itemStack.getComponents().get(ModDataComponents.CHARGES) != null) {
            return itemStack.getComponents().get(ModDataComponents.CHARGES);
        }
        return 0;
    }

    public static void writeChargesToNbt(ItemStack itemStack, int charges) {
        itemStack.set(ModDataComponents.CHARGES, charges);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return DuneCaller.getChargesFromNbt(stack) < 3;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.min(1 + 4 * DuneCaller.getChargesFromNbt(stack), 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ItemStack offHandStack = user.getOffHandStack();
        if (!world.isClient) {
            if (itemStack.getItem() == ModItems.DUNE_CALLER && offHandStack.getItem() == ModItems.DESERT_CORE &&
                    DuneCaller.getChargesFromNbt(itemStack) < 3) {
                DuneCaller.writeChargesToNbt(itemStack, DuneCaller.getChargesFromNbt(itemStack) + 1);
                offHandStack.decrement(1);
                return TypedActionResult.success(itemStack);
            }

            if (DuneCaller.getChargesFromNbt(itemStack) == 0) return TypedActionResult.fail(itemStack);
            SandstormProjectileEntity sandstormProjectileEntity = new SandstormProjectileEntity(user, world);
            sandstormProjectileEntity.setItem(new ItemStack(ModItems.DESERT_CORE));
            sandstormProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 2.0f, 1.0f);
            world.spawnEntity(sandstormProjectileEntity);
            user.getItemCooldownManager().set(this, BsRolePlay.CONFIG.common.getDuneCallerCooldown() * 20);
            DuneCaller.writeChargesToNbt(itemStack, DuneCaller.getChargesFromNbt(itemStack) - 1);
        }
        return TypedActionResult.success(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (BsRolePlay.CONFIG.common.showItemTooltips) {
            tooltip.add(Text.translatable("tooltip.bsroleplay.dune_caller.tooltip"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}