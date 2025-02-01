package banduty.bsroleplay.enchantment;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.enchantment.custom.AutocollectEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> AUTOCOLLECT =
            registerEntityEffect("autocollect", AutocollectEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(BsRolePlay.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        BsRolePlay.LOGGER.info("Registering Mod Enchantment Effects for {}", BsRolePlay.MOD_ID);
    }
}