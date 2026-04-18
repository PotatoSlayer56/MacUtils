package potatoslayer56.macutils.client.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;

public class AdMessage {
  public static void register() {
    ClientCommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext) -> {
      commandDispatcher.register(ClientCommands.literal("admessage").executes(commandContext -> {
        Minecraft.getInstance().player.connection.sendCommand("ad macbox FOR COOLEST BOX PVP / MMO RPG SERVER | SKILLS, ESSENCE, BOSSES & MUCH MORE!!!");
        return 0;
      }));
    }));
  }
}
