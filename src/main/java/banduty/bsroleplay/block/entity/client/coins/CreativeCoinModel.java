package banduty.bsroleplay.block.entity.client.coins;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.coins.CreativeCoinBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CreativeCoinModel extends GeoModel<CreativeCoinBlockEntity> {
    @Override
    public Identifier getModelResource(CreativeCoinBlockEntity animatable) {
        return BsRolePlay.identifierOf("geo/coin.geo.json");
    }

    @Override
    public Identifier getTextureResource(CreativeCoinBlockEntity animatable) {
        return BsRolePlay.identifierOf("textures/block/creative_coins.png");
    }

    @Override
    public Identifier getAnimationResource(CreativeCoinBlockEntity animatable) {
        return null;
    }
}
