package banduty.bsroleplay.networking.packet;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.screen.creative_shop.CreativeShopScreenHandler;
import banduty.bsroleplay.screen.shop.ShopScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public record UpdateCurrencyCounterPacketC2SPacket(int syncId, int amount) implements CustomPayload {
    public static final CustomPayload.Id<UpdateCurrencyCounterPacketC2SPacket> CURRENCY_COUNTER_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("currency_counter"));
    public static final PacketCodec<RegistryByteBuf, UpdateCurrencyCounterPacketC2SPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, UpdateCurrencyCounterPacketC2SPacket::syncId,
            PacketCodecs.INTEGER, UpdateCurrencyCounterPacketC2SPacket::amount,
            UpdateCurrencyCounterPacketC2SPacket::new
    );

    public void handlePacket(ServerPlayNetworking.Context context) {
        MinecraftServer server = context.server();
        ServerPlayerEntity player = context.player();
        UpdateCurrencyCounterPacketC2SPacket packet = new UpdateCurrencyCounterPacketC2SPacket(syncId, amount);
        handle(packet, server, player);
    }

    public static void handle(UpdateCurrencyCounterPacketC2SPacket packet, MinecraftServer server, ServerPlayerEntity player) {
        server.execute(() -> {
            if (player.currentScreenHandler.syncId == packet.syncId) {
                if (player.currentScreenHandler instanceof ShopScreenHandler handler) {
                    if (packet.amount >= 0) {
                        handler.setCurrencyCounter(packet.amount);
                    }
                }

                if (player.currentScreenHandler instanceof CreativeShopScreenHandler handler) {
                    if (packet.amount >= 0) {
                        handler.setCurrencyCounter(packet.amount);
                    }
                }
            }
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return CURRENCY_COUNTER_ID;
    }
}
