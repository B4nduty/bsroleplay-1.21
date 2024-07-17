package banduty.bsroleplay.item.client.blocks;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.blocks.TinyBandutyItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class TinyBandutyItemModel extends GeoModel<TinyBandutyItem> {
    @Override
    public Identifier getModelResource(TinyBandutyItem animatable) {
        return BsRolePlay.identifierOf("geo/tiny_banduty.geo.json");
    }

    @Override
    public Identifier getTextureResource(TinyBandutyItem animatable) {
        return BsRolePlay.identifierOf("textures/block/tiny_banduty.png");
    }

    @Override
    public Identifier getAnimationResource(TinyBandutyItem animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}