package banduty.bsroleplay.item.client.blocks;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.blocks.StrongboxItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class StrongboxItemModel extends GeoModel<StrongboxItem> {
    @Override
    public Identifier getModelResource(StrongboxItem animatable) {
        return BsRolePlay.identifierOf("geo/strongbox.geo.json");
    }

    @Override
    public Identifier getTextureResource(StrongboxItem animatable) {
        return BsRolePlay.identifierOf("textures/block/strongbox.png");
    }

    @Override
    public Identifier getAnimationResource(StrongboxItem animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}