package banduty.bsroleplay.screen;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.shops.CreativeShopBlockEntity;
import banduty.bsroleplay.block.entity.shops.ShopBlockEntity;
import banduty.bsroleplay.item.custom.item.WalletItem;
import banduty.bsroleplay.screen.creative_shop.CreativeShopScreenHandler;
import banduty.bsroleplay.screen.shop.ShopScreenHandler;
import banduty.bsroleplay.screen.wallet.WalletScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static final ScreenHandlerType<WalletScreenHandler> WALLET_SCREEN_HANDLER =
            registerExtended("wallet_gui", new ExtendedScreenHandlerType<>(
                    WalletScreenHandler::new,
                    WalletItem.Data.CODEC
            ));

    public static final ScreenHandlerType<ShopScreenHandler> SHOP_SCREEN_HANDLER =
            registerExtended("shop_gui", new ExtendedScreenHandlerType<>(
                    ShopScreenHandler::new,
                    ShopBlockEntity.Data.CODEC
            ));

    public static final ScreenHandlerType<CreativeShopScreenHandler> CREATIVE_SHOP_SCREEN_HANDLER =
            registerExtended("creative_shop_gui", new ExtendedScreenHandlerType<>(
                    CreativeShopScreenHandler::new,
                    CreativeShopBlockEntity.Data.CODEC
            ));

    private static <T extends ScreenHandler> ScreenHandlerType<T> registerExtended(String name, ScreenHandlerType<T> screenHandlerType) {
        return Registry.register(Registries.SCREEN_HANDLER, BsRolePlay.identifierOf(name), screenHandlerType);
    }

    public static void registerScreenHandlers() {
        BsRolePlay.LOGGER.info("Registering Screen Handlers for " + BsRolePlay.MOD_ID);
    }
}
