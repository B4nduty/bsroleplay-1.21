package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.util.IEntityDataSaver;
import banduty.bsroleplay.util.StaminaData;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;

public record RegenStaminaC2SPacket(boolean active) implements CustomPayload {
    public static final CustomPayload.Id<RegenStaminaC2SPacket> SET_REGEN_STAMINA_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("set_regen_stamina"));
    public static final PacketCodec<RegistryByteBuf, RegenStaminaC2SPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, RegenStaminaC2SPacket::active,
            RegenStaminaC2SPacket::new
    );

    public void handlePacket(ServerPlayNetworking.Context context) {
        ServerPlayerEntity player = context.player();
        StaminaData.setRegenStamina((IEntityDataSaver) player, active);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return SET_REGEN_STAMINA_ID;
    }
}
