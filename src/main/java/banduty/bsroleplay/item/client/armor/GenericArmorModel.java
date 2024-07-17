package banduty.bsroleplay.item.client.armor;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.item.ModItems;
import banduty.bsroleplay.item.custom.armor.GenericArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GenericArmorModel extends GeoModel<GenericArmorItem> {

    @Override
    public Identifier getModelResource(GenericArmorItem animatable) {
        if (animatable == ModItems.CROWN) return BsRolePlay.identifierOf("geo/crown.geo.json");
        if (animatable == ModItems.MINI_CROWN) return BsRolePlay.identifierOf("geo/mini_crown.geo.json");
        if (animatable == ModItems.FEDORA || animatable == ModItems.FEDORA_PURPLE)
            return BsRolePlay.identifierOf("geo/elegant.geo.json");
        if (animatable == ModItems.FUNERAL_MASK) return BsRolePlay.identifierOf("geo/funeral_mask.geo.json");
        if (animatable == ModItems.HALO) return BsRolePlay.identifierOf("geo/halo.geo.json");
        if (animatable == ModItems.LAWYER_BLACKBLUE_CHESTPLATE || animatable == ModItems.LAWYER_LEGGINGS_BLACK ||
                animatable == ModItems.LAWYER_BOOTS_BLACK || animatable == ModItems.LAWYER_BLACKGOLD_CHESTPLATE ||
                animatable == ModItems.LAWYER_BLACKRED_CHESTPLATE || animatable == ModItems.LAWYER_PURPLERED_CHESTPLATE ||
                animatable == ModItems.LAWYER_LEGGINGS_PURPLE || animatable == ModItems.LAWYER_BOOTS_PURPLE)
            return BsRolePlay.identifierOf("geo/lawyer.geo.json");
        if (animatable == ModItems.NEANDERTHAL_CHESTPLATE) return BsRolePlay.identifierOf("geo/neanderthal.geo.json");
        if (animatable == ModItems.CIVIC_CROWN || animatable == ModItems.ROMAN_TOGA)
            return BsRolePlay.identifierOf("geo/roman_emperor.geo.json");
        return Identifier.of("missing");
    }

    @Override
    public Identifier getTextureResource(GenericArmorItem animatable) {
        if (animatable == ModItems.CROWN) return BsRolePlay.identifierOf("textures/armor/crown.png");
        if (animatable == ModItems.MINI_CROWN) return BsRolePlay.identifierOf("textures/armor/mini_crown.png");
        if (animatable == ModItems.FEDORA) return BsRolePlay.identifierOf("textures/armor/elegant.png");
        if (animatable == ModItems.FEDORA_PURPLE) return BsRolePlay.identifierOf("textures/armor/elegant_purple.png");
        if (animatable == ModItems.FUNERAL_MASK) return BsRolePlay.identifierOf("textures/armor/funeral_mask.png");
        if (animatable == ModItems.HALO) return BsRolePlay.identifierOf("textures/armor/halo.png");
        if (animatable == ModItems.LAWYER_BLACKBLUE_CHESTPLATE || animatable == ModItems.LAWYER_LEGGINGS_BLACK ||
                animatable == ModItems.LAWYER_BOOTS_BLACK) return Identifier.of(BsRolePlay.MOD_ID,
                "textures/armor/lawyer_black_and_blue.png");
        if (animatable == ModItems.LAWYER_BLACKGOLD_CHESTPLATE) return Identifier.of(BsRolePlay.MOD_ID,
                "textures/armor/lawyer_black_and_gold.png");
        if (animatable == ModItems.LAWYER_BLACKRED_CHESTPLATE) return Identifier.of(BsRolePlay.MOD_ID,
                "textures/armor/lawyer_black_and_red.png");
        if (animatable == ModItems.LAWYER_PURPLERED_CHESTPLATE || animatable == ModItems.LAWYER_LEGGINGS_PURPLE ||
                animatable == ModItems.LAWYER_BOOTS_PURPLE) return Identifier.of(BsRolePlay.MOD_ID,
                "textures/armor/lawyer_purple_and_red.png");
        if (animatable == ModItems.NEANDERTHAL_CHESTPLATE) return BsRolePlay.identifierOf("textures/armor/neanderthal.png");
        if (animatable == ModItems.CIVIC_CROWN || animatable == ModItems.ROMAN_TOGA)
            return BsRolePlay.identifierOf("textures/armor/roman_emperor.png");
        return Identifier.of("missing");
    }

    @Override
    public Identifier getAnimationResource(GenericArmorItem animatable) {
        return BsRolePlay.identifierOf("animations/generic.animation.json");
    }
}