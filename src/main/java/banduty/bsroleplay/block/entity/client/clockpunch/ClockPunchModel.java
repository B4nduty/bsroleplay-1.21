package banduty.bsroleplay.block.entity.client.clockpunch;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.custom.Clockpunch;
import banduty.bsroleplay.block.entity.ClockPunchBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import software.bernie.geckolib.model.GeoModel;

public class ClockPunchModel extends GeoModel<ClockPunchBlockEntity> {
    @Override
    public Identifier getModelResource(ClockPunchBlockEntity animatable) {
        BlockState state = animatable.getCachedState();
        Direction facing = state.get(Clockpunch.FACING_UP);

        if (facing == Direction.UP) {
            return BsRolePlay.identifierOf("geo/clockpunch_ground.geo.json");
        } else {
            return BsRolePlay.identifierOf("geo/clockpunch_wall.geo.json");
        }
    }

    @Override
    public Identifier getTextureResource(ClockPunchBlockEntity animatable) {
        return BsRolePlay.identifierOf("textures/block/clockpunch.png");
    }

    @Override
    public Identifier getAnimationResource(ClockPunchBlockEntity animatable) {
        return BsRolePlay.identifierOf("animations/clockpunch.animation.json");
    }
}
