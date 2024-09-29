package banduty.bsroleplay.item;

import banduty.bsroleplay.BsRolePlay;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.*;
import net.minecraft.util.*;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> POLICE = register(
            BsRolePlay.MOD_ID +  ":" + "police",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> HOLY = register(
            BsRolePlay.MOD_ID +  ":" + "holy",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 4);
            }),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> PIRATE = register(
            BsRolePlay.MOD_ID +  ":" + "pirate",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 2);
                map.put(ArmorItem.Type.CHESTPLATE, 4);
                map.put(ArmorItem.Type.HELMET, 1);
            }),
            7,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> KING = register(
            BsRolePlay.MOD_ID +  ":" + "king",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 0);
            }),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> MINI_KING = register(
            BsRolePlay.MOD_ID +  ":" + "mini_king",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 0);
            }),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> MASK = register(
            BsRolePlay.MOD_ID +  ":" + "mask",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.BOOTS, 0);
            }),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> NEANDERTHAL = register(
            BsRolePlay.MOD_ID +  ":" + "neanderthal",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.CHESTPLATE, 3);
            }),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> PHARAOH = register(
            BsRolePlay.MOD_ID +  ":" + "pharaoh",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> ROMAN = register(
            BsRolePlay.MOD_ID +  ":" + "roman",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 3);
            }),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> COWBOY = register(
            BsRolePlay.MOD_ID +  ":" + "cowboy",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.CHESTPLATE, 1);
            }),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));
    public static final RegistryEntry<ArmorMaterial> PROTECTION = register(
            BsRolePlay.MOD_ID +  ":" + "protection",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.CHESTPLATE, 4);
                map.put(ArmorItem.Type.LEGGINGS, 3);
                map.put(ArmorItem.Type.BOOTS, 2);
            }),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
            2.0F,
            0.2F,
            () -> Ingredient.ofItems(ModItems.ROLEPLAY_CORE));

    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(Identifier.of(id)));

        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            enumMap.put(type, defense.get(type));
        }
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(id), new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, list, toughness, knockbackResistance));
    }
}