package banduty.bsroleplay.block.entity.client.shops.shop;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.shops.ShopBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ShopModel extends GeoModel<ShopBlockEntity> {
    @Override
    public Identifier getModelResource(ShopBlockEntity animatable) {
        return BsRolePlay.identifierOf("geo/shop.geo.json");
    }

    @Override
    public Identifier getTextureResource(ShopBlockEntity animatable) {
        return BsRolePlay.identifierOf("textures/block/shop.png");
    }

    @Override
    public Identifier getAnimationResource(ShopBlockEntity animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
