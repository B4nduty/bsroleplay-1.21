package banduty.bsroleplay.block.entity.client.coins;

import banduty.bsroleplay.block.entity.coins.CreativeCoinBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CreativeCoinRenderer extends GeoBlockRenderer<CreativeCoinBlockEntity> {
    public CreativeCoinRenderer(BlockEntityRendererFactory.Context context) {
        super(new CreativeCoinModel());
    }
}
