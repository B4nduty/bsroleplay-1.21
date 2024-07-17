package banduty.bsroleplay.item.client.blocks;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.ModItems;
import banduty.bsroleplay.item.custom.blocks.ShopItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ShopItemModel extends GeoModel<ShopItem> {
    @Override
    public Identifier getModelResource(ShopItem animatable) {
        return BsRolePlay.identifierOf("geo/shop.geo.json");
    }

    @Override
    public Identifier getTextureResource(ShopItem animatable) {
        if (animatable == ModItems.SHOP) return BsRolePlay.identifierOf("textures/block/shop.png");
        if (animatable == ModItems.CREATIVE_SHOP) return BsRolePlay.identifierOf("textures/block/creative_shop.png");
        return Identifier.of("missing");
    }

    @Override
    public Identifier getAnimationResource(ShopItem animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}