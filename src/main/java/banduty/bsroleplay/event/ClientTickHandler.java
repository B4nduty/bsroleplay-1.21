package banduty.bsroleplay.event;

import banduty.bsroleplay.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class ClientTickHandler implements ClientTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftClient client) {
        PlayerEntity playerEntity = client.player;
        if (playerEntity != null) {
            if (((IEntityDataSaver) playerEntity).bsroleplay$getPersistentData().getBoolean("handcuffed")) {
                playerEntity.setVelocity(0, 0, 0);
            }
        }
    }
}