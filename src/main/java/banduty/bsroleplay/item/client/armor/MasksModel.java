package banduty.bsroleplay.item.client.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.ModItems;
import banduty.bsroleplay.item.custom.armor.MasksItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MasksModel extends GeoModel<MasksItem> {

    @Override
    public Identifier getModelResource(MasksItem animatable) {
        if (animatable == ModItems.BUNNY_MASK) return BsRolePlay.identifierOf("geo/bunny_mask.geo.json");
        if (animatable == ModItems.DREAM_MASK) return BsRolePlay.identifierOf("geo/dream_mask.geo.json");
        if (animatable == ModItems.DEALER_MASK) return BsRolePlay.identifierOf("geo/dealer_mask.geo.json");
        return Identifier.of("missing");
    }

    @Override
    public Identifier getTextureResource(MasksItem animatable) {
        if (animatable == ModItems.BUNNY_MASK) return BsRolePlay.identifierOf("textures/armor/bunny_mask.png");
        if (animatable == ModItems.DREAM_MASK) return BsRolePlay.identifierOf("textures/armor/dream_mask.png");
        if (animatable == ModItems.DEALER_MASK) return BsRolePlay.identifierOf("textures/armor/dealer_mask.png");
        return Identifier.of("missing");
    }

    @Override
    public Identifier getAnimationResource(MasksItem animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
