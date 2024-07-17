package banduty.bsroleplay.networking;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.networking.packet.PoliceEffectsC2SPacket;
import banduty.bsroleplay.networking.packet.RegenStaminaC2SPacket;
import banduty.bsroleplay.networking.packet.UpdateCurrencyCounterPacketC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ModC2SNetworking {
    static {
        ServerPlayNetworking.registerGlobalReceiver(PoliceEffectsC2SPacket.POLICE_SPEED_ID, PoliceEffectsC2SPacket::handlePacket);
        ServerPlayNetworking.registerGlobalReceiver(RegenStaminaC2SPacket.SET_REGEN_STAMINA_ID, RegenStaminaC2SPacket::handlePacket);
        ServerPlayNetworking.registerGlobalReceiver(UpdateCurrencyCounterPacketC2SPacket.CURRENCY_COUNTER_ID, UpdateCurrencyCounterPacketC2SPacket::handlePacket);
    }

    public static void registerC2SNetworking() {
        BsRolePlay.LOGGER.info("Registering C2S Networking for " + BsRolePlay.MOD_ID);
    }
}
