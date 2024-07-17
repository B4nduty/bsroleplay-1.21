package banduty.bsroleplay.networking;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.networking.packet.*;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public class ModPayloads {
    static {
        registerS2C(BlockedStaminaS2CPacket.BLOCKED_STAMINA_ID, BlockedStaminaS2CPacket.CODEC);
        registerS2C(StaminaIntS2CPacket.STAMINA_INT_ID, StaminaIntS2CPacket.CODEC);
        registerS2C(StaminaBooleanS2CPacket.STAMINA_BOOLEAN_ID, StaminaBooleanS2CPacket.CODEC);
        registerS2C(StaminaZeroS2CPacket.STAMINA_ZERO_ID, StaminaZeroS2CPacket.CODEC);
        registerS2C(VelocityUpdateS2CPacket.VELOCITY_UPDATE_ID, VelocityUpdateS2CPacket.CODEC);
        registerS2C(RegenStaminaS2CPacket.REGEN_STAMINA_ID, RegenStaminaS2CPacket.CODEC);

        registerC2S(PoliceEffectsC2SPacket.POLICE_SPEED_ID, PoliceEffectsC2SPacket.CODEC);
        registerC2S(RegenStaminaC2SPacket.SET_REGEN_STAMINA_ID, RegenStaminaC2SPacket.CODEC);
        registerC2S(UpdateCurrencyCounterPacketC2SPacket.CURRENCY_COUNTER_ID, UpdateCurrencyCounterPacketC2SPacket.CODEC);
    }

    private static <T extends CustomPayload> void registerS2C(CustomPayload.Id<T> packetIdentifier, PacketCodec<RegistryByteBuf, T> codec) {
        PayloadTypeRegistry.playS2C().register(packetIdentifier, codec);
    }

    private static <T extends CustomPayload> void registerC2S(CustomPayload.Id<T> packetIdentifier, PacketCodec<RegistryByteBuf, T> codec) {
        PayloadTypeRegistry.playC2S().register(packetIdentifier, codec);
    }

    public static void registerPayloads() {
        BsRolePlay.LOGGER.info("Registering Payloads for " + BsRolePlay.MOD_ID);
    }
}
