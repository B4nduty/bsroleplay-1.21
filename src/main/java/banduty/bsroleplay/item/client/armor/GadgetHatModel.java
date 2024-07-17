package banduty.bsroleplay.item.client.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.armor.GadgetHatItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GadgetHatModel extends GeoModel<GadgetHatItem> {

    @Override
    public Identifier getModelResource(GadgetHatItem animatable) {
        return BsRolePlay.identifierOf("geo/gadget.geo.json");
    }

    @Override
    public Identifier getTextureResource(GadgetHatItem animatable) {
        return BsRolePlay.identifierOf("textures/armor/gadget.png");
    }

    @Override
    public Identifier getAnimationResource(GadgetHatItem animatable) {
        return BsRolePlay.identifierOf("animations/gadget.animation.json");
    }
}
