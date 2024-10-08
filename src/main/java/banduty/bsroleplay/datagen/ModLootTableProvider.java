package banduty.bsroleplay.datagen;

import banduty.bsroleplay.block.ModBlocks;
import banduty.bsroleplay.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.TINY_BANDUTY, ModItems.TINY_BANDUTY_ITEM);
        addDrop(ModBlocks.SHOP, ModItems.SHOP);
        addDrop(ModBlocks.CREATIVE_SHOP, ModItems.CREATIVE_SHOP);
        addDrop(ModBlocks.COPPER_COIN, ModItems.COPPER_COIN);
        addDrop(ModBlocks.COPPER_COIN_STACK, coinStackDrops(ModBlocks.COPPER_COIN_STACK, ModItems.COPPER_COIN));
        addDrop(ModBlocks.GOLD_COIN, ModItems.GOLD_COIN);
        addDrop(ModBlocks.GOLD_COIN_STACK, coinStackDrops(ModBlocks.GOLD_COIN_STACK, ModItems.GOLD_COIN));
        addDrop(ModBlocks.NETHERITE_COIN, ModItems.NETHERITE_COIN);
        addDrop(ModBlocks.NETHERITE_COIN_STACK, coinStackDrops(ModBlocks.NETHERITE_COIN_STACK, ModItems.NETHERITE_COIN));
        addDrop(ModBlocks.STRONGBOX, ModItems.STRONGBOX);
    }

    public LootTable.Builder coinStackDrops(Block drop, Item item) {
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop,
                ItemEntry.builder(item)
                        .apply(SetCountLootFunction
                                .builder(UniformLootNumberProvider
                                        .create(9.0f, 9.0f)))
        ));
    }
}