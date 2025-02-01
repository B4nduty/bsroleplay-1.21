package banduty.bsroleplay.enchantment.custom;

import banduty.bsroleplay.item.custom.blocks.currency.CoinItem;
import banduty.bsroleplay.item.custom.item.WalletItem;
import banduty.bsroleplay.screen.wallet.WalletScreenHandler;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record AutocollectEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<AutocollectEnchantmentEffect> CODEC = MapCodec.unit(AutocollectEnchantmentEffect::new);
    private static int timeUntilRestart = 0;

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (!(user instanceof PlayerEntity player) || player.currentScreenHandler instanceof WalletScreenHandler) {
            timeUntilRestart = 0;
            return;
        }

        if (timeUntilRestart++ <= 4 * 20 + 1) return;

        Inventory inventory = player.getInventory();
        int currency = WalletItem.getCurrencyFromNbt(context.stack());

        for (int i = 0; i < inventory.size() && currency < WalletItem.MAX_COINS; i++) {
            ItemStack stack = inventory.getStack(i);
            if (!(stack.getItem() instanceof CoinItem coinItem)) continue;
            int amountToAdd = coinItem.currencyValue;

            if (amountToAdd > 0 && currency + amountToAdd <= WalletItem.MAX_COINS) {
                inventory.removeStack(i, 1);
                WalletItem.writeCurrencyToNbt(context.stack(), currency += amountToAdd);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
