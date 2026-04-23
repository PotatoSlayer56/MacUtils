package potatoslayer56.macutils.client.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import potatoslayer56.macutils.client.config.Variables;

public class CustomCommands {
  public static void register() {
    ClientCommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext) -> {
      commandDispatcher.register(ClientCommands.literal("customcommand")
        .then(ClientCommands.argument("number", IntegerArgumentType.integer(1,5))
          .executes(context -> {
            Minecraft.getInstance().gui.getChat().addClientSystemMessage(Component.translatable("prefix.macutils").append(Component.translatable("message.macutils.customcommand.runningtext")).append(Variables.customCommands.get("customCommand%s".formatted(IntegerArgumentType.getInteger(context, "number")))));
            Minecraft.getInstance().player.connection.sendCommand(Variables.customCommands.get("customCommand%s".formatted(IntegerArgumentType.getInteger(context, "number"))));
            return 0;
          })));
    }));
  }
}
