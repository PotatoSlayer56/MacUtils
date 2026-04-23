package potatoslayer56.macutils.client;

import net.fabricmc.api.ClientModInitializer;
import potatoslayer56.macutils.client.commands.*;
import potatoslayer56.macutils.client.config.SaveLoad;
import potatoslayer56.macutils.client.config.Variables;

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
	}
}