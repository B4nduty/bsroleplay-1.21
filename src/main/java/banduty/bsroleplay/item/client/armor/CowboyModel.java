package banduty.bsroleplay.item.client.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.armor.CowboyArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CowboyModel extends GeoModel<CowboyArmorItem> {

    @Override
    public Identifier getModelResource(CowboyArmorItem animatable) {
        return BsRolePlay.identifierOf("geo/cowboy.geo.json");
    }

    @Override
    public Identifier getTextureResource(CowboyArmorItem animatable) {
        return BsRolePlay.identifierOf("textures/armor/cowboy.png");
    }

    @Override
    public Identifier getAnimationResource(CowboyArmorItem animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
