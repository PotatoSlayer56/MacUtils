package potatoslayer56.macutils.client.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import potatoslayer56.macutils.client.networking.MacChatBackEnd;

public class MacChat {
  public static void register(){
    ClientCommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext) -> {
      commandDispatcher.register(ClientCommands.literal("macchat")
        .then(ClientCommands.argument("message", StringArgumentType.greedyString())
          .executes(context -> {
            MacChatBackEnd.sendMqttMessage(StringArgumentType.getString(context, "message"));
            return 0;
          })));
    }));
  }
}
