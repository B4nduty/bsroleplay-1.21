package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record StaminaIntS2CPacket(int staminaInt) implements CustomPayload {
    public static final CustomPayload.Id<StaminaIntS2CPacket> STAMINA_INT_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("stamina_int"));
    public static final PacketCodec<RegistryByteBuf, StaminaIntS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, StaminaIntS2CPacket::staminaInt,
            StaminaIntS2CPacket::new
    );

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientPlayerEntity player = context.player();
        if (player != null) {
            ((IEntityDataSaver) player).bsroleplay$getPersistentData().putInt("stamina_int", staminaInt);
        }
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return STAMINA_INT_ID;
    }
}