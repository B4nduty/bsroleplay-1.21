package banduty.bsroleplay.block.entity.client.coins.stack;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.coins.stack.AmethystCoinStackBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class AmethystCoinStackModel extends GeoModel<AmethystCoinStackBlockEntity> {
    @Override
    public Identifier getModelResource(AmethystCoinStackBlockEntity animatable) {
        return BsRolePlay.identifierOf("geo/coin_stack.geo.json");
    }

    @Override
    public Identifier getTextureResource(AmethystCoinStackBlockEntity animatable) {
        return BsRolePlay.identifierOf("textures/block/amethyst_coins.png");
    }

    @Override
    public Identifier getAnimationResource(AmethystCoinStackBlockEntity animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
