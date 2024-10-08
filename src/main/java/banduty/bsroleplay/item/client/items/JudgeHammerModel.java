package banduty.bsroleplay.item.client.items;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.item.JudgeHammer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class JudgeHammerModel extends GeoModel<JudgeHammer> {

    @Override
    public Identifier getModelResource(JudgeHammer animatable) {
        return BsRolePlay.identifierOf("geo/judge_hammer.geo.json");
    }

    @Override
    public Identifier getTextureResource(JudgeHammer animatable) {
        return BsRolePlay.identifierOf("textures/item/judge_hammer.png");
    }

    @Override
    public Identifier getAnimationResource(JudgeHammer animatable) {
        return BsRolePlay.identifierOf("animations/judge_hammer.animation.json");
    }
}
