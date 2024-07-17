package banduty.bsroleplay.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.specialty.DyeableGeoArmorRenderer;
import software.bernie.geckolib.util.Color;

@Mixin(DyeableGeoArmorRenderer.class)
public interface DyeableGeoArmorRendererInvoker {
    @Invoker(value = "getColorForBone", remap = false)
    Color invokeGetColorForBone(GeoBone bone);
}