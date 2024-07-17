package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record StaminaBooleanS2CPacket(boolean active) implements CustomPayload {
    public static final CustomPayload.Id<StaminaBooleanS2CPacket> STAMINA_BOOLEAN_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("stamina_boolean"));
    public static final PacketCodec<RegistryByteBuf, StaminaBooleanS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, StaminaBooleanS2CPacket::active,
            StaminaBooleanS2CPacket::new
    );

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientPlayerEntity player = context.player();
        if (player != null) {
            ((IEntityDataSaver) player).bsroleplay$getPersistentData().putBoolean("stamina_boolean", active);
        }
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return STAMINA_BOOLEAN_ID;
    }
}
