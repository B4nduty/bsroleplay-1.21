package banduty.bsroleplay.item.client.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.custom.armor.ProtectionClothingItem;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.specialty.DyeableGeoArmorRenderer;
import software.bernie.geckolib.util.Color;

public class ProtectionClothingRenderer extends DyeableGeoArmorRenderer<ProtectionClothingItem> {
    public ProtectionClothingRenderer() {
        super(new ProtectionClothingModel());

        addRenderLayer(new ProtectionClothingOverlay(this));
    }

    @Override
    public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    protected boolean isBoneDyeable(GeoBone bone) {
        return false;
    }

    @Override
    protected @NotNull Color getColorForBone(GeoBone bone) {
        int color = DyedColorComponent.getColor(this.getCurrentStack(), BsRolePlay.getColor(16773120));
        return new Color((0xFF << 24) | color);
    }
}
