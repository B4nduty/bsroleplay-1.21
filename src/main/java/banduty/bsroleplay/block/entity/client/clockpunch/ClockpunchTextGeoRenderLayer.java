package banduty.bsroleplay.block.entity.client.clockpunch;

import banduty.bsroleplay.block.entity.ClockPunchBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class ClockpunchTextGeoRenderLayer extends GeoRenderLayer<ClockPunchBlockEntity> {
    public ClockpunchTextGeoRenderLayer(GeoRenderer<ClockPunchBlockEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack poseStack, ClockPunchBlockEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType,
                       VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        poseStack.translate(0, 0.35f, -0.38f);
        poseStack.scale(0.01f, 0.01f, 0.01f);
        poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
        textRenderer.draw(Text.literal(getTime()), 1f, 1f, 0xffffff, false,
                poseStack.peek().getPositionMatrix(), bufferSource, TextRenderer.TextLayerType.NORMAL, 0,
                getLightLevel(Objects.requireNonNull(animatable.getWorld()), animatable.getPos()));
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

    private String getTime() {
        long currentTime = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(currentTime));
    }
}