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
import net.minecraft.util.Identifier;

public record UpdateCurrencyCounterPacketC2SPacket(int syncId, int increaseAmount) implements CustomPayload {
    public static final CustomPayload.Id<UpdateCurrencyCounterPacketC2SPacket> CURRENCY_COUNTER_ID = new CustomPayload.Id<>(BsRolePlay.identifierOf("currency_counter"));
    public static final PacketCodec<RegistryByteBuf, UpdateCurrencyCounterPacketC2SPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, UpdateCurrencyCounterPacketC2SPacket::syncId,
            PacketCodecs.INTEGER, UpdateCurrencyCounterPacketC2SPacket::increaseAmount,
            UpdateCurrencyCounterPacketC2SPacket::new
    );

    public void handlePacket(ServerPlayNetworking.Context context) {
        MinecraftServer server = context.server();
        ServerPlayerEntity player = context.player();
        UpdateCurrencyCounterPacketC2SPacket packet = new UpdateCurrencyCounterPacketC2SPacket(syncId, increaseAmount);
        handle(packet, server, player);
    }

    public static void handle(UpdateCurrencyCounterPacketC2SPacket packet, MinecraftServer server, ServerPlayerEntity player) {
        server.execute(() -> {
            if (player.currentScreenHandler.syncId == packet.syncId) {
                if (player.currentScreenHandler instanceof ShopScreenHandler handler) {
                    if (packet.increaseAmount > 0) {
                        handler.increaseCurrencyCounter(packet.increaseAmount);
                    } else {
                        handler.decreaseCurrencyCounter(-packet.increaseAmount);
                    }
                }

                if (player.currentScreenHandler instanceof CreativeShopScreenHandler handler) {
                    if (packet.increaseAmount > 0) {
                        handler.increaseCurrencyCounter(packet.increaseAmount);
                    } else {
                        handler.decreaseCurrencyCounter(-packet.increaseAmount);
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
