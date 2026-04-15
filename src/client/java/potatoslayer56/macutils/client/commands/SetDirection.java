package potatoslayer56.macutils.client.commands;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;

public class SetDirection {
  public static void register() {
    ClientCommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext) -> {
      commandDispatcher.register(ClientCommands.literal("setdirection")
        .then(ClientCommands.argument("yaw", FloatArgumentType.floatArg(-180,180))
          .then(ClientCommands.argument("pitch", FloatArgumentType.floatArg(-90,90))
            .executes(context -> {
              Minecraft.getInstance().player.setXRot(FloatArgumentType.getFloat(context, "pitch"));
              Minecraft.getInstance().player.setYRot(FloatArgumentType.getFloat(context, "yaw"));
              return 1;
            }))
        ));
    }));
  }
}
