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

public record BlockedStaminaS2CPacket(boolean blockedStamina) implements CustomPayload {
    public static final CustomPayload.Id<BlockedStaminaS2CPacket> BLOCKED_STAMINA_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("blocked_stamina"));
    public static final PacketCodec<RegistryByteBuf, BlockedStaminaS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, BlockedStaminaS2CPacket::blockedStamina,
            BlockedStaminaS2CPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return BLOCKED_STAMINA_ID;
    }

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientPlayerEntity player = context.player();
        if (player != null) {
            ((IEntityDataSaver) player).bsroleplay$getPersistentData().putBoolean("blocked_stamina", blockedStamina);
        }
    }
}
