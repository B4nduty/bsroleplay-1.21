package banduty.bsroleplay.block.entity.client.shops.creative_shop;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.shops.CreativeShopBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CreativeShopModel extends GeoModel<CreativeShopBlockEntity> {
    @Override
    public Identifier getModelResource(CreativeShopBlockEntity animatable) {
        return BsRolePlay.identifierOf("geo/shop.geo.json");
    }

    @Override
    public Identifier getTextureResource(CreativeShopBlockEntity animatable) {
        return BsRolePlay.identifierOf("textures/block/creative_shop.png");
    }

    @Override
    public Identifier getAnimationResource(CreativeShopBlockEntity animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
