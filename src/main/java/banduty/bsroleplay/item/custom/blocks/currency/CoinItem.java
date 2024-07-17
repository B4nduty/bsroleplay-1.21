
package banduty.bsroleplay.item.custom.blocks.currency;

import banduty.bsroleplay.BsRolePlay;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class CoinItem extends BlockItem {
    public final int currencyValue;

    public CoinItem(Block block, int currencyValue, Settings settings) {
        super(block, settings);
        this.currencyValue = currencyValue;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (BsRolePlay.CONFIG.currency.showCoinPrice) {
            tooltip.add(Text.literal(this.currencyValue + " RP").formatted(Formatting.AQUA));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
