package banduty.bsroleplay.item.client.blocks;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.blocks.ClockpunchItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ClockpunchItemModel extends GeoModel<ClockpunchItem> {
    @Override
    public Identifier getModelResource(ClockpunchItem animatable) {
        return BsRolePlay.identifierOf("geo/clockpunch_ground.geo.json");
    }

    @Override
    public Identifier getTextureResource(ClockpunchItem animatable) {
        return BsRolePlay.identifierOf("textures/block/clockpunch.png");
    }

    @Override
    public Identifier getAnimationResource(ClockpunchItem animatable) {
        return null;
    }
}