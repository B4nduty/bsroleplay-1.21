package banduty.bsroleplay.block.entity;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.ModBlocks;
import banduty.bsroleplay.block.entity.coins.AmethystCoinBlockEntity;
import banduty.bsroleplay.block.entity.coins.CopperCoinBlockEntity;
import banduty.bsroleplay.block.entity.coins.GoldCoinBlockEntity;
import banduty.bsroleplay.block.entity.coins.NetheriteCoinBlockEntity;
import banduty.bsroleplay.block.entity.coins.stack.AmethystCoinStackBlockEntity;
import banduty.bsroleplay.block.entity.coins.stack.CopperCoinStackBlockEntity;
import banduty.bsroleplay.block.entity.coins.stack.GoldCoinStackBlockEntity;
import banduty.bsroleplay.block.entity.coins.stack.NetheriteCoinStackBlockEntity;
import banduty.bsroleplay.block.entity.shops.CreativeShopBlockEntity;
import banduty.bsroleplay.block.entity.shops.ShopBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static void registerAllBlockEntities() {
    }

    public static final BlockEntityType<ShopBlockEntity> SHOP_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("shop_block_entity"),
            BlockEntityType.Builder.create(ShopBlockEntity::new,
                    ModBlocks.SHOP).build());

    public static final BlockEntityType<CreativeShopBlockEntity> CREATIVE_SHOP_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("creative_shop_block_entity"),
            BlockEntityType.Builder.create(CreativeShopBlockEntity::new,
                    ModBlocks.CREATIVE_SHOP).build());

    public static final BlockEntityType<TinyBandutyBlockEntity> TINY_BANDUTY_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("tiny_banduty_block_entity"),
            BlockEntityType.Builder.create(TinyBandutyBlockEntity::new,
                    ModBlocks.TINY_BANDUTY).build());

    public static final BlockEntityType<CopperCoinBlockEntity> COPPER_COIN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("copper_coin_block_entity"),
            BlockEntityType.Builder.create(CopperCoinBlockEntity::new,
                    ModBlocks.COPPER_COIN).build());

    public static final BlockEntityType<GoldCoinBlockEntity> GOLD_COIN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("gold_coin_block_entity"),
            BlockEntityType.Builder.create(GoldCoinBlockEntity::new,
                    ModBlocks.GOLD_COIN).build());

    public static final BlockEntityType<AmethystCoinBlockEntity> AMETHYST_COIN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("amethyst_coin_block_entity"),
            BlockEntityType.Builder.create(AmethystCoinBlockEntity::new,
                    ModBlocks.AMETHYST_COIN).build());

    public static final BlockEntityType<NetheriteCoinBlockEntity> NETHERITE_COIN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("netherite_coin_block_entity"),
            BlockEntityType.Builder.create(NetheriteCoinBlockEntity::new,
                    ModBlocks.NETHERITE_COIN).build());

    public static final BlockEntityType<CopperCoinStackBlockEntity> COPPER_COIN_STACK_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("copper_coin_stack_block_entity"),
            BlockEntityType.Builder.create(CopperCoinStackBlockEntity::new,
                    ModBlocks.COPPER_COIN_STACK).build());

    public static final BlockEntityType<GoldCoinStackBlockEntity> GOLD_COIN_STACK_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("gold_coin_stack_block_entity"),
            BlockEntityType.Builder.create(GoldCoinStackBlockEntity::new,
                    ModBlocks.GOLD_COIN_STACK).build());

    public static final BlockEntityType<AmethystCoinStackBlockEntity> AMETHYST_COIN_STACK_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("amethyst_coin_stack_block_entity"),
            BlockEntityType.Builder.create(AmethystCoinStackBlockEntity::new,
                    ModBlocks.AMETHYST_COIN_STACK).build());

    public static final BlockEntityType<NetheriteCoinStackBlockEntity> NETHERITE_COIN_STACK_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("netherite_coin_stack_block_entity"),
            BlockEntityType.Builder.create(NetheriteCoinStackBlockEntity::new,
                    ModBlocks.NETHERITE_COIN_STACK).build());

    public static final BlockEntityType<StrongboxBlockEntity> STRONGBOX_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            BsRolePlay.identifierOf("strongbox_block_entity"),
            BlockEntityType.Builder.create(StrongboxBlockEntity::new,
                    ModBlocks.STRONGBOX).build());
}