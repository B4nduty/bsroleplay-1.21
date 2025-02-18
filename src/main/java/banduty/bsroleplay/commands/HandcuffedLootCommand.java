package banduty.bsroleplay.commands;

import banduty.bsroleplay.util.InventoryUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class HandcuffedLootCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("bsroleplay")
                .requires(source -> source.hasPermissionLevel(3))
                .then(CommandManager.literal("getHandcuffedLoot")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(HandcuffedLootCommand::run))));
    }

    private static int run(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity targetPlayer;
        try {
            targetPlayer = EntityArgumentType.getPlayer(context, "player");
        } catch (Exception e) {
            context.getSource().sendError(Text.literal("Invalid player specified."));
            return 0;
        }

        InventoryUtil.loadInventoryFromFile(targetPlayer);

        context.getSource().sendFeedback(() -> Text.literal("Restored handcuffed loot for " + targetPlayer.getName().getString()), true);
        targetPlayer.sendMessage(Text.literal("Your handcuffed loot has been restored!"), false);

        return 1;
    }
}