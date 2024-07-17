package banduty.bsroleplay.networking;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.networking.packet.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModS2CNetworking {
    static {
        ClientPlayNetworking.registerGlobalReceiver(BlockedStaminaS2CPacket.BLOCKED_STAMINA_ID, BlockedStaminaS2CPacket::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(StaminaIntS2CPacket.STAMINA_INT_ID, StaminaIntS2CPacket::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(StaminaBooleanS2CPacket.STAMINA_BOOLEAN_ID, StaminaBooleanS2CPacket::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(StaminaZeroS2CPacket.STAMINA_ZERO_ID, StaminaZeroS2CPacket::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(VelocityUpdateS2CPacket.VELOCITY_UPDATE_ID, VelocityUpdateS2CPacket::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(RegenStaminaS2CPacket.REGEN_STAMINA_ID, RegenStaminaS2CPacket::handlePacket);
    }

    public static void registerS2CNetworking() {
        BsRolePlay.LOGGER.info("Registering S2C Networking for " + BsRolePlay.MOD_ID);
    }
}
