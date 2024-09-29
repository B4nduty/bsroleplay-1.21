package banduty.bsroleplay.block.entity.client;

import banduty.bsroleplay.block.entity.StrongboxBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class StrongboxRenderer extends GeoBlockRenderer<StrongboxBlockEntity> {
    public StrongboxRenderer(BlockEntityRendererFactory.Context context) {
        super(new StrongboxModel());
    }
}
