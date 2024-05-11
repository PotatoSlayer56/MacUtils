package net.macutils;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.macutils.gui.MainGUI;
import net.macutils.gui.MainScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;


public class MacUtils implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("macutils");
	public static final String VERSION = "1.2.0 for 1.20.4";
	public static Boolean AUTOFEED = false;
	public static Boolean AUTOFEEDNOKICK = true;


	private static KeyBinding trashKeyBind;
	private static KeyBinding openGUIKeyBind;

	@Override
	public void onInitialize() {
		LOGGER.info("MacUtils Loaded");

		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		PlayerEntity player = minecraftClient.player;

		// COMMANDS
		commandMacUtils();
		commandMacLocations();
		commandMacProgression();
		commandMacNumberGame();

		// KEYBINDS
		registerKeyBinds();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(MinecraftClient.getInstance().player != null){
				if (AUTOFEED){
					if (MinecraftClient.getInstance().player.getHungerManager().getFoodLevel() < 8){
						if (AUTOFEEDNOKICK) {
							MinecraftClient.getInstance().player.networkHandler.sendChatCommand("feed");
							MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("§aMacUtils %s".formatted(VERSION)));
							AUTOFEEDNOKICK = false;
						}
					}
					else if (MinecraftClient.getInstance().player.getHungerManager().getFoodLevel() == 20){
						AUTOFEEDNOKICK = true;
					}
				}
			}
			while (trashKeyBind.wasPressed()) {
				MinecraftClient.getInstance().player.networkHandler.sendChatCommand("trash");
			}
			while (openGUIKeyBind.wasPressed()) {
				MinecraftClient.getInstance().setScreen(new MainScreen(new MainGUI()));
			}
		});
	}

	public void sendFeedback(CommandContext<FabricClientCommandSource> context, String str) {
		context.getSource().sendFeedback(Text.literal(str));
	}

	public void commandMacUtils(){
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
				.register(literal("macutils").executes(context -> {
							sendFeedback(context,":)");

							return 0;
						})
						.then(literal("version").executes(context -> {
							sendFeedback(context,"§aMacUtils %s".formatted(VERSION));
							return 0;
						}))
						.then(literal("discord").executes(context -> {
							sendFeedback(context,"§aMacUtils Official Discord: discord.gg/n5bSMPdseB");
							return 0;
						}))
						.then(literal("autofeed").executes(context -> {
							if (AUTOFEED) {
								AUTOFEED = false;
								sendFeedback(context, "§aAuto Feed §6Disabled");
							}
							else if (!AUTOFEED) {
								AUTOFEED = true;
								sendFeedback(context, "§aAuto Feed §6Enabled");
							}
							return 0;
						}))
						.then(literal("test").executes(context -> {
							MinecraftClient.getInstance().inGameHud.setOverlayMessage(Text.literal("§aMacUtils %s".formatted(VERSION)), false);
							return 0;
						}))
						.then(literal("help").executes(context -> {
							sendFeedback(context,
									"§aMacUtils Commands\n" +
											"§6/macutils §aShows Main GUI\n" +
											"§6/macutils version §aShows MacUtils Version\n" +
											"§6/macutils discord §aShows MacUtils Discord Link\n" +
											"§6/maclocations [<item>] §aShows Location of Item"
							);
							return 0;
						}))));
	}

	public void commandMacLocations(){
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
				.register(literal("maclocations").executes(context -> {
							sendFeedback(context,
									"§aCurrent locations:\n" +
											"§aHeads: §6rezli, pepe, godfrog, mocsek, monkey, palm\n" +
											"§aTalismans: §6brick, hay, amethyst, minionpoop, thor, bigmac");
							return 0;
						})
						.then(literal("head").executes(context -> {
									return 0;
								})
								.then(literal("rezli").executes(context -> {
									sendFeedback(context,"§aTop left of giant MacBox sign");
									return 0;
								}))
								.then(literal("pepe").executes(context -> {
									sendFeedback(context,"§aInside the left most tree on portal mountain");
									return 0;
								}))
								.then(literal("godfrog").executes(context -> {
									sendFeedback(context,"§aOn top of portal in shadow world");
									return 0;
								}))
								.then(literal("mocsek").executes(context -> {
									sendFeedback(context,"§aOn top of a stack of boxes in ikea world");
									return 0;
								}))
								.then(literal("monkey").executes(context -> {
									sendFeedback(context, "§aBottom left of animal kingdom inside the rock");
									return 0;
								}))
								.then(literal("palm").executes(context -> {
									sendFeedback(context,"§aOn top of oasis portal in portal room 3");
									return 0;
								})))
						.then(literal("talisman").executes(context -> {
									return 0;
								})
								.then(literal("fishing").executes(context -> {
									sendFeedback(context,"§aBack left on top of nether");
									return 0;
								}))
								.then(literal("brick").executes(context -> {
									sendFeedback(context,"§aOn top of portal in heaven world");
									return 0;
								}))
								.then(literal("hay").executes(context -> {
									sendFeedback(context, "§aOn the middle crossbeam in the barn in farm world");
									return 0;
								}))
								.then(literal("amethyst").executes(context -> {
									sendFeedback(context, "§aInside the crystal in crystal world");
									return 0;
								}))
								.then(literal("minionpoop").executes(context -> {
									sendFeedback(context, "§aBehind the right side of the minion in minion world");
									return 0;
								}))
								.then(literal("thor").executes(context -> {
									sendFeedback(context, "§aOn top of spawn portal in portal room 1");
									return 0;
								}))
								.then(literal("bigmac").executes(context -> {
									sendFeedback(context, "§aOn the roof on the mcdonalds in mcdonalds world");
									return 0;
								})))));
	}

	public void commandMacProgression(){
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
				.register(literal("macprogression").executes(context -> {
							return 0;
						})
						.then(literal("pickaxe").executes(context -> {
							sendFeedback(context,
									"§aObtain §4Vader Pick§a,\n" +
											"§aObtain §61,600,000 §amining essence,\n" +
											"§aBuy §6§lMiner's Pickaxe §athen upgrade to tier §65\n" +
											"§aFrom there upgrade to tier §610 §aover time"
							);
							return 0;
						}))));
	}

	public void commandMacNumberGame(){
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
				.register(literal("macnumbergame").then(argument("value", IntegerArgumentType.integer()).executes(context -> {
					final int value = IntegerArgumentType.getInteger(context, "value");
					for (int i = 0; i < value; i++) {
						MinecraftClient.getInstance().player.networkHandler.sendChatMessage(String.valueOf(i));
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
					return 0;
				}))));
	}

	public void registerKeyBinds(){
		openGUIKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"Open GUI",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_P,
				"MacUtils"
		));

		trashKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"/trash",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_B,
				"MacUtils"
		));
	}
}