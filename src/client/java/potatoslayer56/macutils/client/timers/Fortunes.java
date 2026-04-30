package potatoslayer56.macutils.client.timers;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import potatoslayer56.macutils.client.config.Variables;

public class Fortunes {
  public static void register() {
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (Variables.TIMBERFORTUNETIMER > 0) {
        Variables.TIMBERFORTUNETIMER -= 1;
        if (Variables.TIMBERFORTUNETIMER == 0) {
          Minecraft.getInstance().gui.setTitle(Component.translatable("title.macutils.fortunes.timber"));
          Minecraft.getInstance().player.playSound(SoundEvents.PLAYER_LEVELUP, 2.0f, 0.0f);
        }
      }

      if (Variables.MININGFORTUNETIMER > 0) {
        Variables.MININGFORTUNETIMER -= 1;
        if (Variables.MININGFORTUNETIMER == 0) {
          Minecraft.getInstance().gui.setTitle(Component.translatable("title.macutils.fortunes.mining"));
          Minecraft.getInstance().player.playSound(SoundEvents.PLAYER_LEVELUP, 2.0f, 0.0f);
        }
      }
    });
  }
}
