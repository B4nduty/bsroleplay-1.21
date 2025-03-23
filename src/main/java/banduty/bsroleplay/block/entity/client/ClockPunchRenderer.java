package banduty.bsroleplay.block.entity.client;

import banduty.bsroleplay.block.entity.ClockPunchBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ClockPunchRenderer extends GeoBlockRenderer<ClockPunchBlockEntity> {
    public ClockPunchRenderer(BlockEntityRendererFactory.Context context) {
        super(new ClockPunchModel());
    }
}
