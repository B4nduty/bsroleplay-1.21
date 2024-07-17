package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record StaminaZeroS2CPacket(boolean active) implements CustomPayload {
    public static final CustomPayload.Id<StaminaZeroS2CPacket> STAMINA_ZERO_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("stamina_zero"));
    public static final PacketCodec<RegistryByteBuf, StaminaZeroS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, StaminaZeroS2CPacket::active,
            StaminaZeroS2CPacket::new
    );

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientPlayerEntity player = context.player();
        if (player != null) {
            ((IEntityDataSaver) player).bsroleplay$getPersistentData().putBoolean("stamina_zero", active);
        }
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return STAMINA_ZERO_ID;
    }
}