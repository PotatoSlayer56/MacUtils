package potatoslayer56.macutils.client;

import net.fabricmc.api.ClientModInitializer;
import potatoslayer56.macutils.client.commands.Autofeed;
import potatoslayer56.macutils.client.commands.LockPitch;
import potatoslayer56.macutils.client.commands.LockYaw;
import potatoslayer56.macutils.client.commands.SetDirection;
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
	}
}