package banduty.bsroleplay.item.client.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.ModItems;
import banduty.bsroleplay.item.custom.armor.PirateArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PirateArmorModel extends GeoModel<PirateArmorItem> {

    @Override
    public Identifier getModelResource(PirateArmorItem animatable) {
        return BsRolePlay.identifierOf("geo/pirate.geo.json");
    }

    @Override
    public Identifier getTextureResource(PirateArmorItem animatable) {
        if (animatable == ModItems.COCKED_HAT) return BsRolePlay.identifierOf("textures/armor/cocked_hat.png");
        if (animatable == ModItems.BICORNE || animatable == ModItems.PIRATE_CHESTPLATE
                || animatable == ModItems.PIRATE_LEGGINGS || animatable == ModItems.PIRATE_BOOTS)
            return BsRolePlay.identifierOf("textures/armor/pirate_attire.png");
        if (animatable == ModItems.BANDANNA || animatable == ModItems.BUCCANEER_CHESTPLATE
                || animatable == ModItems.BUCCANEER_LEGGINGS || animatable == ModItems.BUCCANEER_BOOTS)
            return BsRolePlay.identifierOf("textures/armor/buccaneer_attire.png");
        return Identifier.of("missing");
    }

    @Override
    public Identifier getAnimationResource(PirateArmorItem animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
