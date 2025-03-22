package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.screen.shop.ShopScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record UpdateCurrencyPacketS2CPacket(int syncId, int amount) implements CustomPayload {
    public static final Id<UpdateCurrencyPacketS2CPacket> S2C_CURRENCY_COUNTER_ID = new Id<>(BsRolePlay.identifierOf("s2c_currency_counter"));
    public static final PacketCodec<RegistryByteBuf, UpdateCurrencyPacketS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, UpdateCurrencyPacketS2CPacket::syncId,
            PacketCodecs.INTEGER, UpdateCurrencyPacketS2CPacket::amount,
            UpdateCurrencyPacketS2CPacket::new
    );

    public void handlePacket(ClientPlayNetworking.Context context) {
        MinecraftClient client = context.client();
        ClientPlayerEntity player = context.player();
        UpdateCurrencyPacketS2CPacket packet = new UpdateCurrencyPacketS2CPacket(syncId, amount);
        handle(packet, client, player);
    }

    public static void handle(UpdateCurrencyPacketS2CPacket packet, MinecraftClient server, ClientPlayerEntity player) {
        server.execute(() -> {
            if (player.currentScreenHandler instanceof ShopScreenHandler shopHandler &&
                    shopHandler.syncId == packet.syncId()) {
                shopHandler.setCurrencyCounter(packet.amount());
            }
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return S2C_CURRENCY_COUNTER_ID;
    }
}
