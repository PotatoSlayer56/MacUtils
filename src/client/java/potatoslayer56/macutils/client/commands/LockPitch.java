package potatoslayer56.macutils.client.commands;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.Component;
import potatoslayer56.macutils.client.MacUtilsClient;
import potatoslayer56.macutils.client.config.Variables;

public class LockPitch {
  public static void register() {
    ClientCommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext) -> {
      commandDispatcher.register(ClientCommands.literal("lockpitch")
        .then(ClientCommands.argument("pitch", FloatArgumentType.floatArg(-90,90))
          .executes(context -> {
            Variables.values.get("pitchlock").replace("angle", FloatArgumentType.getFloat(context, "pitch"));
            MacUtilsClient.sendClientMessage(Component.translatable("prefix.macutils").append(Component.translatable("message.macutils.lockpitch.pitchset.start")).append(Component.literal(String.valueOf(Variables.values.get("pitchlock").get("angle"))).withColor(15248405)).append(Component.translatable("message.macutils.lockpitch.pitchset.end")));
            return 0;
          })
        ));
    }));

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (client.player != null && Variables.PITCHLOCKED) {
        client.player.setXRot(Variables.values.get("pitchlock").get("angle"));
      }
    });
  }
}
