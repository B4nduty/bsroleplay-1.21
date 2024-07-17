package banduty.bsroleplay.block.entity.client.coins.stack;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.coins.stack.GoldCoinStackBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GoldCoinStackModel extends GeoModel<GoldCoinStackBlockEntity> {
    @Override
    public Identifier getModelResource(GoldCoinStackBlockEntity animatable) {
        return BsRolePlay.identifierOf("geo/coin_stack.geo.json");
    }

    @Override
    public Identifier getTextureResource(GoldCoinStackBlockEntity animatable) {
        return BsRolePlay.identifierOf("textures/block/gold_coins.png");
    }

    @Override
    public Identifier getAnimationResource(GoldCoinStackBlockEntity animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
