package potatoslayer56.macutils.client.commands;

import com.google.errorprone.annotations.Var;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.Component;
import potatoslayer56.macutils.client.MacUtilsClient;
import potatoslayer56.macutils.client.config.Variables;

public class Autofeed {
  public static void register() {
    ClientCommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext) -> {
      commandDispatcher.register(ClientCommands.literal("autofeed").executes(context -> {
        Variables.toggles.get("autofeed").replace("enabled",!Variables.toggles.get("autofeed").get("enabled"));
        if (Variables.toggles.get("autofeed").get("enabled")) {
          MacUtilsClient.sendClientMessage(Component.translatable("message.macutils.autofeed.enabled"));
        }
        else {
          MacUtilsClient.sendClientMessage(Component.translatable("message.macutils.autofeed.disabled"));
        }
        return 0;
      }));
    }));

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (Variables.toggles.get("autofeed").get("enabled") && client.player != null) {
        if (Variables.AUTOFEEDNOKICK && client.player.getFoodData().getFoodLevel() <= Variables.values.get("autofeed").get("threshold")) {
          Variables.AUTOFEEDNOKICK = false;
          client.player.connection.sendCommand("feed");
        }
        else if (!Variables.AUTOFEEDNOKICK && client.player.getFoodData().getFoodLevel() == 20) {
          Variables.AUTOFEEDNOKICK = true;
        }
      }
    });
  }
}
