package banduty.bsroleplay.block.entity.client.coins;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.coins.NetheriteCoinBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class NetheriteCoinModel extends GeoModel<NetheriteCoinBlockEntity> {
    @Override
    public Identifier getModelResource(NetheriteCoinBlockEntity animatable) {
        return BsRolePlay.identifierOf("geo/coin.geo.json");
    }

    @Override
    public Identifier getTextureResource(NetheriteCoinBlockEntity animatable) {
        return BsRolePlay.identifierOf("textures/block/netherite_coins.png");
    }

    @Override
    public Identifier getAnimationResource(NetheriteCoinBlockEntity animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}
