package banduty.bsroleplay.item.client.items;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.ModItems;
import banduty.bsroleplay.item.custom.item.BriefCase;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BriefcaseModel extends GeoModel<BriefCase> {

    @Override
    public Identifier getModelResource(BriefCase animatable) {
        return BsRolePlay.identifierOf("geo/briefcase.geo.json");
    }

    @Override
    public Identifier getTextureResource(BriefCase animatable) {
        if (animatable == ModItems.BRIEFCASE) return BsRolePlay.identifierOf("textures/item/briefcase.png");
        if (animatable == ModItems.VIOLET_BRIEFCASE) return BsRolePlay.identifierOf("textures/item/violet_briefcase.png");
        if (animatable == ModItems.BLACK_BRIEFCASE) return BsRolePlay.identifierOf("textures/item/black_briefcase.png");
        return Identifier.of("missing");
    }

    @Override
    public Identifier getAnimationResource(BriefCase animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
