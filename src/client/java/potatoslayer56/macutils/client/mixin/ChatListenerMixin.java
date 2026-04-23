package potatoslayer56.macutils.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import potatoslayer56.macutils.client.config.Variables;

import java.awt.*;

@Mixin(ChatListener.class)
public class ChatListenerMixin {
  @Inject(method = "handleSystemMessage", at = @At("TAIL"))
  private void fortuneTimers(Component message, boolean remote, CallbackInfo ci){
    if(message.getString().contains("Mining Fortune ability used!")){
      Variables.MININGFORTUNETIMER = 6 * 60 * 20;
    }
    else if(message.getString().contains("Timber Fortune ability used!")){
      Variables.TIMBERFORTUNETIMER = 6 * 60 * 20;
    }
  }

  @Inject(method = "handleSystemMessage", at = @At("TAIL"))
  private void slayerTimers(Component message, boolean remote, CallbackInfo ci){
    if(message.getString().contains("ZOMBIE SLAYER")){
      Variables.SLAYERACTIVE = true;
    }
    else if(message.getString().contains("BOSS DEFEATED!")){
      Variables.SLAYERACTIVE = false;
      Minecraft.getInstance().gui.getChat().addClientSystemMessage(Component.translatable("prefix.macutils").append(Component.translatable("message.macutils.timers.slayerfinish.start").append(Component.literal(String.valueOf(Variables.SLAYERTIME / 20)).withColor(15248405)).append(Component.translatable("message.macutils.timers.slayerfinish.end"))));
      Variables.SLAYERTIME = 0;
    }
  }
}
