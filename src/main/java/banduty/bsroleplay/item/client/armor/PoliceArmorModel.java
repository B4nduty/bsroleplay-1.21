package banduty.bsroleplay.item.client.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.armor.PoliceArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PoliceArmorModel extends GeoModel<PoliceArmorItem> {

    @Override
    public Identifier getModelResource(PoliceArmorItem animatable) {
        return BsRolePlay.identifierOf("geo/police_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(PoliceArmorItem animatable) {
        return BsRolePlay.identifierOf("textures/armor/police_armor.png");
    }

    @Override
    public Identifier getAnimationResource(PoliceArmorItem animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
