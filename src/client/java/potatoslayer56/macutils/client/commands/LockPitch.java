package potatoslayer56.macutils.client.commands;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import potatoslayer56.macutils.client.config.Variables;

public class LockPitch {
  public static void register() {
    ClientCommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext) -> {
      commandDispatcher.register(ClientCommands.literal("lockpitch")
        .then(ClientCommands.argument("pitch", FloatArgumentType.floatArg(-90,90))
          .executes(context -> {
            Variables.PITCHLOCK = FloatArgumentType.getFloat(context, "pitch");
            return 0;
          })
        ));
    }));

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (client.player != null && Variables.PITCHLOCKED) {
        client.player.setXRot(Variables.PITCHLOCK);
      }
    });
  }
}
