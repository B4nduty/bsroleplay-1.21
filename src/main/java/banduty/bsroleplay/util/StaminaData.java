package banduty.bsroleplay.util;

import banduty.bsroleplay.networking.packet.*;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class StaminaData {
    public static void setStamina(IEntityDataSaver player, boolean staminaBoolean) {
        NbtCompound nbt = player.bsroleplay$getPersistentData();
        nbt.putBoolean("stamina_boolean", staminaBoolean);
        syncStaminaBoolean(staminaBoolean, (ServerPlayerEntity) player);
    }

    public static void setBlockedStamina(IEntityDataSaver player, boolean blockedStamina) {
        NbtCompound nbt = player.bsroleplay$getPersistentData();
        nbt.putBoolean("blocked_stamina", blockedStamina);
        syncBlockedStamina(blockedStamina, (ServerPlayerEntity) player);
    }

    public static void setZero(IEntityDataSaver player, boolean staminaZero) {
        NbtCompound nbt = player.bsroleplay$getPersistentData();
        nbt.putBoolean("stamina_zero", staminaZero);
        syncStaminaZero(staminaZero, (ServerPlayerEntity) player);
    }

    public static void setRegenStamina(IEntityDataSaver player, boolean regenStamina) {
        NbtCompound nbt = player.bsroleplay$getPersistentData();
        nbt.putBoolean("regen_stamina", regenStamina);
        syncRegenStamina(regenStamina, (ServerPlayerEntity) player);
    }

    public static void addStamina(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.bsroleplay$getPersistentData();
        int staminaInt = nbt.getInt("stamina_int");
        int totalStamina = 60;
        if (player instanceof ServerPlayerEntity playerEntity) {
            AdvancementEntry netherAdvancement = playerEntity.server.getAdvancementLoader().get(Identifier.ofVanilla("nether/root"));
            AdvancementEntry endAdvancement = playerEntity.server.getAdvancementLoader().get(Identifier.ofVanilla("end/kill_dragon"));
            if (playerEntity.getAdvancementTracker().getProgress(endAdvancement).isDone()) {
                totalStamina = 180;
            } else if (playerEntity.getAdvancementTracker().getProgress(netherAdvancement).isDone()) {
                totalStamina = 120;
            }
        }
        if (staminaInt + amount >= totalStamina) {
            staminaInt = totalStamina;
        } else {
            staminaInt += amount;
        }

        nbt.putInt("stamina_int", staminaInt);
        if (player instanceof ServerPlayerEntity) {
            syncStamina(staminaInt, (ServerPlayerEntity) player);
        }
    }

    public static void removeStamina(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.bsroleplay$getPersistentData();
        int stamina_int = nbt.getInt("stamina_int");
        if (stamina_int - amount < 0) {
            stamina_int = 0;
        } else {
            stamina_int -= amount;
        }

        nbt.putInt("stamina_int", stamina_int);
        syncStamina(stamina_int, (ServerPlayerEntity) player);
    }

    public static void syncStamina(int staminaInt, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new StaminaIntS2CPacket(staminaInt));
    }

    public static void syncStaminaBoolean(boolean staminaBoolean, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new StaminaBooleanS2CPacket(staminaBoolean));
    }

    public static void syncStaminaZero(boolean staminaZero, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new StaminaZeroS2CPacket(staminaZero));
    }

    public static void syncRegenStamina(boolean regenStamina, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new RegenStaminaS2CPacket(regenStamina));
    }

    public static void syncBlockedStamina(boolean blockedStamina, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new BlockedStaminaS2CPacket(blockedStamina));
    }
}