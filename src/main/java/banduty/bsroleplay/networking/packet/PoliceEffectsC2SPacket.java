package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.util.IEntityDataSaver;
import banduty.bsroleplay.util.StaminaData;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public record PoliceEffectsC2SPacket(boolean active) implements CustomPayload {
    public static final CustomPayload.Id<PoliceEffectsC2SPacket> POLICE_SPEED_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("police_speed"));
    public static final PacketCodec<RegistryByteBuf, PoliceEffectsC2SPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, PoliceEffectsC2SPacket::active,
            PoliceEffectsC2SPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return POLICE_SPEED_ID;
    }

    public void handlePacket(ServerPlayNetworking.Context context) {
        ServerPlayerEntity player = context.player();
        if (!((IEntityDataSaver) player).bsroleplay$getPersistentData().getBoolean("stamina_zero") && active) {
            AdvancementEntry netherAdvancement = player.server.getAdvancementLoader().get(Identifier.ofVanilla("nether/root"));
            AdvancementEntry endAdvancement = player.server.getAdvancementLoader().get(Identifier.ofVanilla("end/kill_dragon"));
            StaminaData.removeStamina((IEntityDataSaver) player, 1);
            int speedAmplifier = 2;
            if (player.getAdvancementTracker().getProgress(endAdvancement).isDone()) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2,
                        2, false, false, false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2,
                        1, false, false, false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2,
                        0, false, false, false));

                speedAmplifier = 4;
            } else if (player.getAdvancementTracker().getProgress(netherAdvancement).isDone()) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2,
                        1, false, false, false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2,
                        0, false, false, false));
                speedAmplifier = 3;
            }
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2,
                    speedAmplifier, false, false, false));
        }
    }
}
