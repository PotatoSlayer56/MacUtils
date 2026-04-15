package potatoslayer56.macutils.client;

import com.google.errorprone.annotations.Var;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.dialog.Input;
import org.lwjgl.glfw.GLFW;
import potatoslayer56.macutils.MacUtils;
import potatoslayer56.macutils.client.config.ConfigScreen;
import potatoslayer56.macutils.client.config.Variables;

public class Keybinds {
  public static KeyMapping.Category CATEGORY = KeyMapping.Category.register(
    Identifier.fromNamespaceAndPath(MacUtils.MOD_ID, "custom_category")
  );

  public static KeyMapping openConfigScreenKeyBind = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.open-config-screen",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static KeyMapping enderChestKeybind = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.ender-chest",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static KeyMapping trashKeybind = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.trash",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static KeyMapping customKeybind1 = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.custom-command-1",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static KeyMapping customKeybind2 = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.custom-command-2",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static KeyMapping customKeybind3 = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.custom-command-3",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static KeyMapping customKeybind4 = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.custom-command-4",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static KeyMapping customKeybind5 = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.custom-command-5",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static KeyMapping lockPitch = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.lockpitch",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static KeyMapping lockYaw = KeyMappingHelper.registerKeyMapping(
    new KeyMapping(
      "key.macutils.lockyaw",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_UNKNOWN,
      CATEGORY
    )
  );

  public static void register() {
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      while (openConfigScreenKeyBind.consumeClick()) {
        if (client.player != null) {
          client.setScreen(new ConfigScreen(Component.translatable("screen.title.main")));
        }
      }

      while (enderChestKeybind.consumeClick()) {
        if (client.player != null) {
          client.player.connection.sendCommand("ec");
        }
      }

      while (trashKeybind.consumeClick()) {
        if (client.player != null) {
          client.player.connection.sendCommand("trash");
        }
      }

      while (customKeybind1.consumeClick()) {
        if (client.player != null) {
          client.player.connection.sendCommand(Variables.customCommands.get("customCommand1"));
        }
      }

      while (customKeybind2.consumeClick()) {
        if (client.player != null) {
          client.player.connection.sendCommand(Variables.customCommands.get("customCommand2"));
        }
      }

      while (customKeybind3.consumeClick()) {
        if (client.player != null) {
          client.player.connection.sendCommand(Variables.customCommands.get("customCommand3"));
        }
      }

      while (customKeybind4.consumeClick()) {
        if (client.player != null) {
          client.player.connection.sendCommand(Variables.customCommands.get("customCommand4"));
        }
      }

      while (customKeybind5.consumeClick()) {
        if (client.player != null) {
          client.player.connection.sendCommand(Variables.customCommands.get("customCommand5"));
        }
      }

      while (lockPitch.consumeClick()) {
        if (client.player != null) {
          Variables.PITCHLOCKED = !Variables.PITCHLOCKED;
          if (Variables.PITCHLOCKED) {
            client.player.sendSystemMessage(Component.translatable("message.macutils.lockpitch.enabled"));
          }
          else {
            client.player.sendSystemMessage(Component.translatable("message.macutils.lockpitch.disabled"));
          }
        }
      }

      while (lockYaw.consumeClick()) {
        if (client.player != null) {
          Variables.YAWLOCKED = !Variables.YAWLOCKED;
          if (Variables.YAWLOCKED) {
            client.player.sendSystemMessage(Component.translatable("message.macutils.lockyaw.enabled"));
          }
          else {
            client.player.sendSystemMessage(Component.translatable("message.macutils.lockyaw.disabled"));
          }
        }
      }
    });
  }
}
