package potatoslayer56.macutils.client.timers;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import potatoslayer56.macutils.client.config.Variables;

public class HealingItems {
  public static void register(){
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if(Variables.FRYTIMERENBALED){
        if(Variables.FRYTIMER > 0){
          Variables.FRYTIMER -= 1;
          if(Variables.FRYTIMER == 0){
            Minecraft.getInstance().gui.setTitle(Component.translatable("title.macutils.healingitems.fry"));
          }
        }
      }
      if(Variables.MOLTENBLADETIMERENABLED){
        if(Variables.MOLTENBLADETIMER > 0){
          Variables.MOLTENBLADETIMER -= 1;
          if(Variables.MOLTENBLADETIMER == 0){
            Minecraft.getInstance().gui.setTitle(Component.translatable("title.macutils.healingitems.moltenblade"));
          }
        }
      }
    });
  }
}
