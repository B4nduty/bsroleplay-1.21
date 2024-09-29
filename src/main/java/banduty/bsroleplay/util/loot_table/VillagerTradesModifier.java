package banduty.bsroleplay.util.loot_table;

import banduty.bsroleplay.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

public class VillagerTradesModifier {
    public static void modifyVillagerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 5,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 64),
                            new ItemStack(ModItems.MEDICBAG, 1),
                            2, 40, 0.0f));
                });
    }
}
