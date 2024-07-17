package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record VelocityUpdateS2CPacket(int playerEntity, double velocityX, double velocityY, double velocityZ) implements CustomPayload {
    public static final CustomPayload.Id<VelocityUpdateS2CPacket> VELOCITY_UPDATE_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("velocity_update"));
    public static final PacketCodec<RegistryByteBuf, VelocityUpdateS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, VelocityUpdateS2CPacket::playerEntity,
            PacketCodecs.DOUBLE, VelocityUpdateS2CPacket::velocityX,
            PacketCodecs.DOUBLE, VelocityUpdateS2CPacket::velocityY,
            PacketCodecs.DOUBLE, VelocityUpdateS2CPacket::velocityZ,
            VelocityUpdateS2CPacket::new
    );
    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld clientWorld = context.client().world;

        if (clientWorld != null) {
            Entity entity = clientWorld.getEntityById(playerEntity);
            if (entity != null) {
                entity.setVelocity(velocityX, velocityY, velocityZ);
            }
        }
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return VELOCITY_UPDATE_ID;
    }
}

