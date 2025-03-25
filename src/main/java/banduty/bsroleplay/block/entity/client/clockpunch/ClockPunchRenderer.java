package banduty.bsroleplay.block.entity.client.clockpunch;

import banduty.bsroleplay.block.custom.Clockpunch;
import banduty.bsroleplay.block.entity.ClockPunchBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ClockPunchRenderer extends GeoBlockRenderer<ClockPunchBlockEntity> {
    public ClockPunchRenderer(BlockEntityRendererFactory.Context context) {
        super(new ClockPunchModel());

        addRenderLayer(new ClockpunchTextGeoRenderLayer(this));
    }

    @Override
    protected void rotateBlock(Direction facing, MatrixStack poseStack) {
        switch (facing) {
            case SOUTH -> poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
            case WEST -> poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90));
            case EAST -> poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(270));
            case NORTH -> {}
        }
    }

    @Override
    protected Direction getFacing(ClockPunchBlockEntity blockEntity) {
        if (blockEntity != null && blockEntity.getWorld() != null) {
            BlockState state = blockEntity.getWorld().getBlockState(blockEntity.getPos());
            if (state.contains(Clockpunch.FACING)) {
                return state.get(Clockpunch.FACING);
            }
        }
        return Direction.NORTH;
    }
}
