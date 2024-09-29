package banduty.bsroleplay.item.client.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.ModItems;
import banduty.bsroleplay.item.custom.armor.PirateArmorItem;
import banduty.bsroleplay.mixin.DyeableGeoArmorRendererInvoker;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import software.bernie.geckolib.util.Color;

public class PirateArmorOverlay extends GeoRenderLayer<PirateArmorItem> {
    public PirateArmorOverlay(GeoRenderer<PirateArmorItem> entityRenderer) {
        super(entityRenderer);
    }

    private GeoBone getBoneYouAreInterestedIn(BakedGeoModel bakedModel) {
        return bakedModel.topLevelBones().stream().findFirst().orElse(null);
    }

    @Override
    public void render(MatrixStack poseStack, PirateArmorItem animatable, BakedGeoModel bakedModel, RenderLayer renderType,
                       VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, renderType,
                buffer, partialTick, packedLight, packedOverlay, Color.WHITE.argbInt());

        Identifier TEXTURE = Identifier.of("missing");
        if (animatable == ModItems.COCKED_HAT) TEXTURE = Identifier.of(BsRolePlay.MOD_ID, "textures/armor/cocked_hat_overlay.png");
        if (animatable == ModItems.BICORNE || animatable == ModItems.PIRATE_CHESTPLATE
                || animatable == ModItems.PIRATE_LEGGINGS || animatable == ModItems.PIRATE_BOOTS)
            TEXTURE = Identifier.of(BsRolePlay.MOD_ID, "textures/armor/pirate_attire_overlay.png");
        if (animatable == ModItems.BANDANNA || animatable == ModItems.BUCCANEER_CHESTPLATE
                || animatable == ModItems.BUCCANEER_LEGGINGS || animatable == ModItems.BUCCANEER_BOOTS)
            TEXTURE = Identifier.of(BsRolePlay.MOD_ID, "textures/armor/buccaneer_attire_overlay.png");

        RenderLayer armorRenderType = RenderLayer.getArmorCutoutNoCull(TEXTURE);
        GeoBone bone = getBoneYouAreInterestedIn(bakedModel);
        DyeableGeoArmorRendererInvoker invoker = (DyeableGeoArmorRendererInvoker) getRenderer();

        Color colorObj = invoker.invokeGetColorForBone(bone);
        int color = colorObj.getColor();
        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, armorRenderType,
                bufferSource.getBuffer(armorRenderType), partialTick, packedLight, packedOverlay, color);
    }
}