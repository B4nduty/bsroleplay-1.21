package banduty.bsroleplay.item.client.items;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.item.PoliceBaton;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PoliceBatonModel extends GeoModel<PoliceBaton> {

    @Override
    public Identifier getModelResource(PoliceBaton animatable) {
        return BsRolePlay.identifierOf("geo/police_baton.geo.json");
    }

    @Override
    public Identifier getTextureResource(PoliceBaton animatable) {
        return BsRolePlay.identifierOf("textures/item/police_baton.png");
    }

    @Override
    public Identifier getAnimationResource(PoliceBaton animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
