package potatoslayer56.macutils.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import potatoslayer56.macutils.client.config.Variables;

@Mixin(ChatListener.class)
public class ChatListenerMixin {
  @Inject(method = "handleSystemMessage", at = @At("TAIL"))
  private void fortuneTimers(Component message, boolean remote, CallbackInfo ci){
    if(Variables.FORTUNETIMERSENABLED){
      if(message.getString().contains("Mining Fortune ability used!")){
        Variables.MININGFORTUNETIMER = 6 * 60 * 20;
      }
      else if(message.getString().contains("Timber Fortune ability used!")){
        Variables.TIMBERFORTUNETIMER = 6 * 60 * 20;
      }
    }
  }

  @Inject(method = "handleSystemMessage", at = @At("TAIL"))
  private void slayerTimers(Component message, boolean remote, CallbackInfo ci){
    if(Variables.SLAYERTIMERSENABLED){
      if(message.getString().contains("ZOMBIE SLAYER") || message.getString().contains("FROG SLAYER")){
        Variables.SLAYERACTIVE = true;
      }
      else if(message.getString().contains("BOSS DEFEATED!")){
        Variables.SLAYERACTIVE = false;
        Minecraft.getInstance().gui.getChat().addClientSystemMessage(Component.translatable("prefix.macutils").append(Component.translatable("message.macutils.timers.slayerfinish.start").append(Component.literal(String.valueOf(Variables.SLAYERTIME / 20)).withColor(15248405)).append(Component.translatable("message.macutils.timers.slayerfinish.end"))));
        Variables.SLAYERTIME = 0;
      }
    }
  }

  @Inject(method = "handleSystemMessage", at = @At("TAIL"))
  private void slayerSpawnMessages(Component message, boolean remote, CallbackInfo ci){
    if(Variables.SLAYERMESSAGESENABLED){
      if(message.getString().toLowerCase().contains("miniboss has spawned")){
        Minecraft.getInstance().gui.setTitle(Component.translatable("title.macutils.slayers.miniboss"));
      }
      else if(message.getString().toLowerCase().contains("boss has spawned")){
        Minecraft.getInstance().gui.setTitle(Component.translatable("title.macutils.slayers.boss"));
      }
    }
  }

  @Inject(method = "handleSystemMessage", at = @At("TAIL"))
  private void healItemTimers(Component message, boolean remote, CallbackInfo ci){
    if(Variables.FRYTIMERENBALED){
      if(message.getString().contains("FRENCH FRY") && message.getString().contains("10 heals")){
        Variables.FRYTIMER = 60 * 20;
      }
    }
    if(Variables.MOLTENBLADETIMERENABLED){
      if(message.getString().contains("MOLTEN BLADE")){
        if(message.getString().contains("9")){
          Variables.MOLTENBLADETIMER = 15 * 20;
        }
        else if(message.getString().contains("8")){
          Variables.MOLTENBLADETIMER = 30 * 20;
        }
        else if(message.getString().contains("7")){
          Variables.MOLTENBLADETIMER = 45 * 20;
        }
        else if(message.getString().contains("6")){
          Variables.MOLTENBLADETIMER = 60 * 20;
        }
        else if(message.getString().contains("5")){
          Variables.MOLTENBLADETIMER = 75 * 20;
        }
        else if(message.getString().contains("4")){
          Variables.MOLTENBLADETIMER = 90 * 20;
        }
        else if(message.getString().contains("3")){
          Variables.MOLTENBLADETIMER = 105 * 20;
        }
        else if(message.getString().contains("2")){
          Variables.MOLTENBLADETIMER = 120 * 20;
        }
        else if(message.getString().contains("1")){
          Variables.MOLTENBLADETIMER = 135 * 20;
        }
        else if(message.getString().contains("0")){
          Variables.MOLTENBLADETIMER = 150 * 20;
        }
      }
    }
  }

  //@Inject(method = "handleSystemMessage", at = @At("TAIL"))
  private void copyRNGMessages(Component message, boolean remote, CallbackInfo ci){
    if(Variables.COPYRNGMESSAGESENABLED){
      if(message.getString().contains("RARE DROP") || message.getString().contains("CRAZY RARE DROP") || message.getString().contains("INSANE DROP")){
        Minecraft.getInstance().keyboardHandler.setClipboard(message.getString());
        Minecraft.getInstance().gui.getChat().addClientSystemMessage(Component.translatable("prefix.macutils").append(Component.translatable("message.macutils.rngcopy")));
      }
    }
  }
}