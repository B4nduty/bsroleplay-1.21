package banduty.bsroleplay.item.client.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.armor.PirateArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PirateArmorModel extends GeoModel<PirateArmorItem> {

    @Override
    public Identifier getModelResource(PirateArmorItem animatable) {
        return BsRolePlay.identifierOf("geo/pirate_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(PirateArmorItem animatable) {
        return BsRolePlay.identifierOf("textures/armor/pirate_armor.png");
    }

    @Override
    public Identifier getAnimationResource(PirateArmorItem animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
