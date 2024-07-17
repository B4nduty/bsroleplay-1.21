package banduty.bsroleplay.effect;

import banduty.bsroleplay.BsRolePlay;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> SANDSTORM = registerStatusEffect(
            "sandstorm",
            new SandstormEffect(StatusEffectCategory.HARMFUL, 16749568)
    );
    public static final RegistryEntry<StatusEffect> TIDAL_SURGE = registerStatusEffect(
            "tidal_surge",
            new TidalSurgeEffect(StatusEffectCategory.HARMFUL, 16776960)
    );

    public static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect effect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, BsRolePlay.identifierOf(name), effect);
    }

    public static void registerEffects() {
    }
}
