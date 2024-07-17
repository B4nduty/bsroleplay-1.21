package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record RegenStaminaS2CPacket(boolean active) implements CustomPayload {
    public static final CustomPayload.Id<RegenStaminaS2CPacket> REGEN_STAMINA_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("regen_stamina"));
    public static final PacketCodec<RegistryByteBuf, RegenStaminaS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, RegenStaminaS2CPacket::active,
            RegenStaminaS2CPacket::new
    );
    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientPlayerEntity player = context.player();
        if (player != null) {
            ((IEntityDataSaver) player).bsroleplay$getPersistentData().putBoolean("regen_stamina", active);
        }
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return REGEN_STAMINA_ID;
    }
}
