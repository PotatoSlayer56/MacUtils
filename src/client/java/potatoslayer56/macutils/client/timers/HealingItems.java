package potatoslayer56.macutils.client.timers;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import potatoslayer56.macutils.client.config.Variables;

public class HealingItems {
  public static void register(){
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if(Variables.toggles.get("fry").get("enabled")){
        if(Variables.FRYTIMER > 0){
          Variables.FRYTIMER -= 1;
          if(Variables.FRYTIMER == 0){
            Minecraft.getInstance().gui.setTitle(Component.translatable("title.macutils.healingitems.fry"));
            Minecraft.getInstance().player.playSound(SoundEvents.PLAYER_LEVELUP, 2.0f, 0.0f);
          }
        }
      }

      if(Variables.toggles.get("moltenblade").get("enabled")){
        if(Variables.MOLTENBLADETIMER > 0){
          Variables.MOLTENBLADETIMER -= 1;
          if(Variables.MOLTENBLADETIMER == 0){
            Minecraft.getInstance().gui.setTitle(Component.translatable("title.macutils.healingitems.moltenblade"));
            Minecraft.getInstance().player.playSound(SoundEvents.PLAYER_LEVELUP, 2.0f, 0.0f);
          }
        }
      }
    });
  }
}
