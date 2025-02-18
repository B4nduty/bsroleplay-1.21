package banduty.bsroleplay.util;

import banduty.bsroleplay.networking.packet.HandcuffedS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class Handcuffed {
    public static void setHandcuffed(ServerPlayerEntity player, boolean handcuffed) {
        NbtCompound nbt = ((IEntityDataSaver) player).bsroleplay$getPersistentData();
        nbt.putBoolean("handcuffed", handcuffed);
        ServerPlayNetworking.send(player, new HandcuffedS2CPacket(handcuffed));
    }
}
