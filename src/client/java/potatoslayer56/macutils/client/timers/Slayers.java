package potatoslayer56.macutils.client.timers;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import potatoslayer56.macutils.client.config.Variables;

public class Slayers {
  public static void register() {
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if(Variables.SLAYERACTIVE) {
        Variables.SLAYERTIME += 1;
      }
    });
  }
}
