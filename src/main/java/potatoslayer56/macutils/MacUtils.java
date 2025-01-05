package potatoslayer56.macutils;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import potatoslayer56.macutils.commands.autofeed;

public class MacUtils implements ModInitializer {
	public static final String MOD_ID = "macutils";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static boolean AUTOFEED = false;
	public static boolean AUTOFEEDNOKICK = true;

	public KeyBinding trashKeyBind;
	public KeyBinding enderKeyBind;

	@Override
	public void onInitialize() {

		registerCommands();
		LOGGER.info("MacUtils - Commands Registered");

		registerKeyBinds();
		LOGGER.info("MacUtils - Keybinds Registered");

		LOGGER.info("MacUtils - MacUtils Loaded");
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(MinecraftClient.getInstance().player != null){
				if(AUTOFEED){
					if(MinecraftClient.getInstance().player.getHungerManager().getFoodLevel() < 8){
						if(AUTOFEEDNOKICK){
							MinecraftClient.getInstance().getNetworkHandler().sendChatCommand("feed");
							MinecraftClient.getInstance().player.sendMessage(Text.literal("§aFed automatically by MacUtils"));
							AUTOFEEDNOKICK = false;
						}
					}
					else if(MinecraftClient.getInstance().player.getHungerManager().getFoodLevel() == 20){
						AUTOFEEDNOKICK = true;
					}
				}
				if(trashKeyBind.wasPressed()){
					MinecraftClient.getInstance().getNetworkHandler().sendChatCommand("trash");
				}
				if(enderKeyBind.wasPressed()){
					MinecraftClient.getInstance().getNetworkHandler().sendChatCommand("ec");
				}
			}
		});
	}

	public void registerCommands(){
		autofeed.register();
	}

	public void registerKeyBinds(){
		trashKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"/trash",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				"MacUtils"
		));
		enderKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"/ec",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				"MacUtils"
		));
	}
}