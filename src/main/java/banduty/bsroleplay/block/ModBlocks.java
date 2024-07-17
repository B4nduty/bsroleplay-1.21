package banduty.bsroleplay.block;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.custom.CreativeShop;
import banduty.bsroleplay.block.custom.Shop;
import banduty.bsroleplay.block.custom.TinyBanduty;
import banduty.bsroleplay.block.custom.coins.CoinBlock;
import banduty.bsroleplay.block.custom.coins.CoinStackBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {

    public static final Block SHOP = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("shop"),
            new Shop(AbstractBlock.Settings.copy(Blocks.OAK_WOOD).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block CREATIVE_SHOP = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("creative_shop"),
            new CreativeShop(AbstractBlock.Settings.copy(Blocks.OAK_WOOD).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block TINY_BANDUTY = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("tiny_banduty"),
            new TinyBanduty(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL).sounds(BlockSoundGroup.WOOL).strength(1.0f).nonOpaque()));

    public static final Block COPPER_COIN = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("copper_coin"),
            new CoinBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.METAL).strength(3.0F, 6.0F).nonOpaque()));

    public static final Block GOLD_COIN = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("gold_coin"),
            new CoinBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.METAL).strength(3.0F, 6.0F).nonOpaque()));

    public static final Block AMETHYST_COIN = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("amethyst_coin"),
            new CoinBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(3.0F, 6.0F).nonOpaque()));

    public static final Block NETHERITE_COIN = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("netherite_coin"),
            new CoinBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.METAL).strength(50.0F, 1200.0F).nonOpaque()));

    public static final Block COPPER_COIN_STACK = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("copper_coin_stack"),
            new CoinStackBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.METAL).strength(5.0F, 6.0F).nonOpaque()));

    public static final Block GOLD_COIN_STACK = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("gold_coin_stack"),
            new CoinStackBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.METAL).strength(5.0F, 6.0F).nonOpaque()));

    public static final Block AMETHYST_COIN_STACK = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("amethyst_coin_stack"),
            new CoinStackBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(5.0F, 6.0F).nonOpaque()));

    public static final Block NETHERITE_COIN_STACK = Registry.register(Registries.BLOCK, BsRolePlay.identifierOf("netherite_coin_stack"),
            new CoinStackBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.METAL).strength(80.0F, 8.0F).nonOpaque()));

    public static void registerModBlocks() {
        BsRolePlay.LOGGER.info("Registering ModBlocks for " + BsRolePlay.MOD_ID);
    }
}