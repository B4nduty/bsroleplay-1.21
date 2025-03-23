package banduty.bsroleplay.block.entity.client;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.ClockPunchBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ClockPunchModel extends GeoModel<ClockPunchBlockEntity> {
    @Override
    public Identifier getModelResource(ClockPunchBlockEntity animatable) {
        return BsRolePlay.identifierOf("geo/strongbox.geo.json");
    }

    @Override
    public Identifier getTextureResource(ClockPunchBlockEntity animatable) {
        return BsRolePlay.identifierOf("textures/block/strongbox.png");
    }

    @Override
    public Identifier getAnimationResource(ClockPunchBlockEntity animatable) {
        return null;
    }
}
