package potatoslayer56.macutils.client;

import net.fabricmc.api.ClientModInitializer;
import potatoslayer56.macutils.client.commands.*;
import potatoslayer56.macutils.client.config.SaveLoad;
import potatoslayer56.macutils.client.config.Variables;
import potatoslayer56.macutils.client.timers.Fortunes;
import potatoslayer56.macutils.client.timers.HealingItems;
import potatoslayer56.macutils.client.timers.Slayers;

public class MacUtilsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Variables.initialise();

		SaveLoad.createConfig();
		SaveLoad.loadConfig();

		Keybinds.register();
		Autofeed.register();
		SetDirection.register();
		LockPitch.register();
		LockYaw.register();
		AdMessage.register();
		CustomCommands.register();

		Fortunes.register();
		Slayers.register();
		HealingItems.register();
	}
}