package banduty.bsroleplay.block.entity.client;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.StrongboxBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class StrongboxModel extends GeoModel<StrongboxBlockEntity> {
    @Override
    public Identifier getModelResource(StrongboxBlockEntity animatable) {
        return BsRolePlay.identifierOf("geo/strongbox.geo.json");
    }

    @Override
    public Identifier getTextureResource(StrongboxBlockEntity animatable) {
        return BsRolePlay.identifierOf("textures/block/strongbox.png");
    }

    @Override
    public Identifier getAnimationResource(StrongboxBlockEntity animatable) {
        return BsRolePlay.identifierOf("animations/strongbox.animation.json");
    }
}
