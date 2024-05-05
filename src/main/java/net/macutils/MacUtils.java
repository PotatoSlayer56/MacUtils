package net.macutils;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.impl.registry.sync.trackers.vanilla.BlockItemTracker;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;


public class MacUtils implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("macutils");
	public static final String VERSION = "1.1.0 for 1.20.6";

	@Override
	public void onInitialize() {
		LOGGER.info("MacUtils Loaded");
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
		.register(literal("macutils").executes(context -> {
							sendFeedback(context,"§aGUI Here");
					return 0;
				}
			)
		.then(literal("version").executes(context -> {
					sendFeedback(context,"§aMacUtils Version %s".formatted(VERSION));
					return 0;
				}
			)
		)
		.then(literal("discord").executes(context -> {
					sendFeedback(context,"§aMacUtils Official Discord: discord.gg/n5bSMPdseB");
					return 0;
				}
			)
		)
		.then(literal("help").executes(context -> {
					sendFeedback(context,
							"§aMacUtils Commands\n" +
							"§6/macutils §aShows Main GUI\n" +
							"§6/macutils version §aShows MacUtils Version\n" +
							"§6/macutils discord §aShows MacUtils Discord Link\n" +
							"§6/maclocations [<item>] §aShows Location of Item");
					return 0;
				}
			)
		)
		));
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
		.register(literal("maclocations").executes(context -> {
							sendFeedback(context,
									"§aCurrent locations:\n" +
									"§aHeads: §6rezli, pepe, godfrog, mocsek, monkey, palm\n" +
									"§aTalismans: §6brick, hay, amethyst, minionpoop, thor, bigmac");
					return 0;
				}
			)
		.then(literal("head").executes(context -> {
					return 0;
				}
			)
		.then(literal("rezli").executes(context -> {
					sendFeedback(context,"§aTop left of giant MacBox sign");
					return 0;
				}
			)
		)
		.then(literal("pepe").executes(context -> {
					sendFeedback(context,"§aInside the left most tree on portal mountain");
					return 0;
				}
			)
		)
		.then(literal("godfrog").executes(context -> {
					sendFeedback(context,"§aOn top of portal in shadow world");
					return 0;
				}
			)
		)
		.then(literal("mocsek").executes(context -> {
					sendFeedback(context,"§aOn top of a stack of boxes in ikea world");
					return 0;
				}
			)
		)
		.then(literal("monkey").executes(context -> {
					sendFeedback(context, "§aBottom left of animal kingdom inside the rock");
					return 0;
				}
			)
		)
		.then(literal("palm").executes(context -> {
					sendFeedback(context,"§aOn top of oasis portal in portal room 3");
					return 0;
				}
			)
		)
		)
		.then(literal("talisman").executes(context -> {
					return 0;
				}
			)
		.then(literal("fishing").executes(context -> {
					sendFeedback(context,"§aBack left on top of nether");
					return 0;
				}
			)
		)
		.then(literal("brick").executes(context -> {
					sendFeedback(context,"§aOn top of portal in heaven world");
					return 0;
				}
			)
		)
		.then(literal("hay").executes(context -> {
					sendFeedback(context, "§aOn the middle crossbeam in the barn in farm world");
					return 0;
				}
			)
		)
		.then(literal("amethyst").executes(context -> {
					sendFeedback(context, "§aInside the crystal in crystal world");
					return 0;
				}
			)
		)
		.then(literal("minionpoop").executes(context -> {
					sendFeedback(context, "§aBehind the right side of the minion in minion world");
					return 0;
				}
			)
		)
		.then(literal("thor").executes(context -> {
					sendFeedback(context, "§aOn top of spawn portal in portal room 1");
					return 0;
				}
			)
		)
		.then(literal("bigmac").executes(context -> {
					sendFeedback(context, "§aOn the roof on the mcdonalds in mcdonalds world");
					return 0;
				}
			)
		)
		)
		));
	}

	public static void sendFeedback(CommandContext<FabricClientCommandSource> context, String str) {
		context.getSource().sendFeedback(Text.literal(str));
	}
}