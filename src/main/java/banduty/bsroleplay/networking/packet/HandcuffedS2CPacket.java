package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record HandcuffedS2CPacket(boolean handcuffed) implements CustomPayload {
    public static final Id<HandcuffedS2CPacket> HANDCUFFED = new Id<>(BsRolePlay.identifierOf("handcuffed"));
    public static final PacketCodec<RegistryByteBuf, HandcuffedS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, HandcuffedS2CPacket::handcuffed,
            HandcuffedS2CPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return HANDCUFFED;
    }

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientPlayerEntity player = context.player();
        if (player != null) {
            ((IEntityDataSaver) player).bsroleplay$getPersistentData().putBoolean("handcuffed", handcuffed);
        }
    }
}
