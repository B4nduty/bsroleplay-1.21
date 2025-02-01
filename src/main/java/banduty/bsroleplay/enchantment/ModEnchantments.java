package banduty.bsroleplay.enchantment;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.enchantment.custom.AutocollectEnchantmentEffect;
import banduty.bsroleplay.util.ModTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> AUTOCOLLECT =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(BsRolePlay.MOD_ID, "autocollect"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, AUTOCOLLECT, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ModTags.WALLET),
                        items.getOrThrow(ModTags.WALLET),
                        25,
                        4,
                        Enchantment.leveledCost(2, 5),
                        Enchantment.leveledCost(9, 7),
                        2,
                        AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.IN_ENCHANTING_TABLE))
                .addEffect(EnchantmentEffectComponentTypes.TICK, new AutocollectEnchantmentEffect()));
    }


    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}