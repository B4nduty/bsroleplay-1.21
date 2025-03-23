
package banduty.bsroleplay;

import banduty.bsroleplay.block.entity.ModBlockEntities;
import banduty.bsroleplay.block.entity.client.ClockPunchRenderer;
import banduty.bsroleplay.block.entity.client.StrongboxRenderer;
import banduty.bsroleplay.block.entity.client.TinyBandutyRenderer;
import banduty.bsroleplay.block.entity.client.coins.*;
import banduty.bsroleplay.block.entity.client.coins.stack.AmethystCoinStackRenderer;
import banduty.bsroleplay.block.entity.client.coins.stack.CopperCoinStackRenderer;
import banduty.bsroleplay.block.entity.client.coins.stack.GoldCoinStackRenderer;
import banduty.bsroleplay.block.entity.client.coins.stack.NetheriteCoinStackRenderer;
import banduty.bsroleplay.block.entity.client.shops.creative_shop.CreativeShopRenderer;
import banduty.bsroleplay.block.entity.client.shops.shop.ShopRenderer;
import banduty.bsroleplay.client.GrayscaleOverlay;
import banduty.bsroleplay.client.StaminaOverlay;
import banduty.bsroleplay.config.ModConfigs;
import banduty.bsroleplay.entity.ModEntities;
import banduty.bsroleplay.event.ClientTickHandler;
import banduty.bsroleplay.event.KeyInputHandler;
import banduty.bsroleplay.event.LivingEntityDamageHandler;
import banduty.bsroleplay.item.ModItems;
import banduty.bsroleplay.networking.ModS2CNetworking;
import banduty.bsroleplay.screen.ModScreenHandlers;
import banduty.bsroleplay.screen.clockpunch.ClockPunchScreen;
import banduty.bsroleplay.screen.creative_shop.CreativeShopScreen;
import banduty.bsroleplay.screen.shop.ShopScreen;
import banduty.bsroleplay.screen.strongbox.StrongboxScreen;
import banduty.bsroleplay.screen.wallet.WalletScreen;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BsRolePlayClient implements ClientModInitializer {
	public static final String MOD_ID = "bsroleplay";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ModConfigs CONFIG;

	private static int angle = 0;
	private static int angleTickCounter = 0;
	private static int height = 0;
	private static boolean increasing = true;

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			angleTickCounter++;
			if (angleTickCounter >= 1) {
				angleTickCounter = 0;
				angle = (angle + 1) % 361;
				if (angle == 360) angle = 0;
				if (increasing) {
					height++;
					if (height >= 100) increasing = false;
				} else {
					height--;
					if (height <= 0) increasing = true;
				}
			}
		});
		BlockEntityRendererRegistry.register(ModBlockEntities.SHOP_BLOCK_ENTITY, ShopRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.CREATIVE_SHOP_BLOCK_ENTITY, CreativeShopRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.TINY_BANDUTY_BLOCK_ENTITY, TinyBandutyRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.COPPER_COIN_BLOCK_ENTITY, CopperCoinRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.GOLD_COIN_BLOCK_ENTITY, GoldCoinRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.AMETHYST_COIN_BLOCK_ENTITY, AmethystCoinRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.NETHERITE_COIN_BLOCK_ENTITY, NetheriteCoinRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.CREATIVE_COIN_BLOCK_ENTITY, CreativeCoinRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.COPPER_COIN_STACK_BLOCK_ENTITY, CopperCoinStackRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.GOLD_COIN_STACK_BLOCK_ENTITY, GoldCoinStackRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.AMETHYST_COIN_STACK_BLOCK_ENTITY, AmethystCoinStackRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.NETHERITE_COIN_STACK_BLOCK_ENTITY, NetheriteCoinStackRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.STRONGBOX_BLOCK_ENTITY, StrongboxRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntities.CLOCKPUNCH_BLOCK_ENTITY, ClockPunchRenderer::new);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
						tintIndex > 0 ? -1 : DyedColorComponent.getColor(stack, getColor(10511680)), ModItems.WALLET,
				ModItems.COCKED_HAT, ModItems.BICORNE, ModItems.PIRATE_CHESTPLATE, ModItems.PIRATE_LEGGINGS,
				ModItems.PIRATE_BOOTS, ModItems.BANDANNA, ModItems.BUCCANEER_CHESTPLATE, ModItems.BUCCANEER_LEGGINGS,
				ModItems.BUCCANEER_BOOTS, ModItems.GADGET_HAT);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
						tintIndex > 0 ? -1 : DyedColorComponent.getColor(stack, getColor(16773120)), ModItems.PROTECTION_HELMET,
				ModItems.PROTECTION_CHESTPLATE, ModItems.PROTECTION_LEGGINGS, ModItems.PROTECTION_BOOTS);

		HandledScreens.register(ModScreenHandlers.WALLET_SCREEN_HANDLER, WalletScreen::new);
		HandledScreens.register(ModScreenHandlers.SHOP_SCREEN_HANDLER, ShopScreen::new);
		HandledScreens.register(ModScreenHandlers.CREATIVE_SHOP_SCREEN_HANDLER, CreativeShopScreen::new);
		HandledScreens.register(ModScreenHandlers.STRONGBOX_SCREEN_HANDLER, StrongboxScreen::new);
		HandledScreens.register(ModScreenHandlers.CLOCKPUNCH_SCREEN_HANDLER, ClockPunchScreen::new);

		HudRenderCallback.EVENT.register(new StaminaOverlay());
		HudRenderCallback.EVENT.register(new GrayscaleOverlay());

		KeyInputHandler.register();

		ModS2CNetworking.registerS2CNetworking();

		EntityRendererRegistry.register(ModEntities.SANDSTORM_PROJECTILE, FlyingItemEntityRenderer::new);

		ClientTickEvents.START_CLIENT_TICK.register(new ClientTickHandler());

		ServerLivingEntityEvents.ALLOW_DAMAGE.register(new LivingEntityDamageHandler());
	}

	public static int getAngle() {
		return angle;
	}

	public static int getHeight() {
		return height;
	}

	public static int getColor(int rgb) {
		return (255 << 24) | rgb;
	}

	public static Identifier identifierOf(String name) {
		return Identifier.of(MOD_ID, name);
	}
}
