package banduty.bsroleplay.event;

import banduty.bsroleplay.networking.packet.PoliceEffectsC2SPacket;
import banduty.bsroleplay.networking.packet.RegenStaminaC2SPacket;
import banduty.bsroleplay.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String BANDUTY_ROLEPLAY = "key.category.bsroleplay";
    public static final String KEY_POLICE_SPEED = "key.bsroleplay.police_speed";

    public static KeyBinding policeSpeed;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayerEntity playerEntity = MinecraftClient.getInstance().player;
            if (playerEntity != null) {
                int stamina = ((IEntityDataSaver) playerEntity).bsroleplay$getPersistentData().getInt("stamina_int");
                boolean staminaZero = ((IEntityDataSaver) playerEntity).bsroleplay$getPersistentData().getBoolean("stamina_zero");
                if(policeSpeed.isPressed() && ((IEntityDataSaver) playerEntity).bsroleplay$getPersistentData()
                        .getBoolean("stamina_boolean") && stamina > 0 && !staminaZero) {
                    ClientPlayNetworking.send(new PoliceEffectsC2SPacket(true));
                    ClientPlayNetworking.send(new RegenStaminaC2SPacket(false));
                }

                if (!policeSpeed.isPressed()) {
                    ClientPlayNetworking.send(new PoliceEffectsC2SPacket(false));
                    ClientPlayNetworking.send(new RegenStaminaC2SPacket(true));
                }
            }
        });
    }

    public static void register() {
        policeSpeed = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_POLICE_SPEED,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                BANDUTY_ROLEPLAY
        ));

        registerKeyInputs();
    }
}
