package net.macutils;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;


public class MacUtils implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("macutils");
	public static final String VERSION = "1.0.0";

	@Override
	public void onInitialize() {
		LOGGER.info("MacUtils Loaded");
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
		.register(literal("macutils").executes(context -> {
					context.getSource().sendFeedback(Text.literal("§aGUI Here"));
					return 0;
				}
			)
		.then(literal("version").executes(context -> {
					context.getSource().sendFeedback(Text.literal("§aMacUtils Version %s for 1.20.6".formatted(VERSION)));
					return 0;
				}
			)
		)
		.then(literal("discord").executes(context -> {
					context.getSource().sendFeedback(Text.literal("§aMacUtils Official Discord: discord.gg/n5bSMPdseB"));
					return 0;
				}
			)
		)
		.then(literal("help").executes(context -> {
					context.getSource().sendFeedback(Text.literal(
							"§aMacUtils Commands\n" +
							"§6/macutils §aShows Main GUI\n" +
							"§6/macutils version §aShows MacUtils Version\n" +
							"§6/macutils discord §aShows MacUtils Discord Link\n" +
							"§6/maclocation [<item>] §aShows Location of Item"));
					return 0;
				}
			)
		)
		));
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
		.register(literal("maclocation").executes(context -> {
					context.getSource().sendFeedback(Text.literal("§aShows location of a certain item"));
					return 0;
				}
			)
		.then(literal("rezli").executes(context -> {
					context.getSource().sendFeedback(Text.literal("§aTop left of giant MacBox sign"));
					return 0;
				}
			)
		)
		.then(literal("pepe").executes(context -> {
					context.getSource().sendFeedback(Text.literal("§aLeft most tree on portal mountain"));
					return 0;
				}
			)
		)
		.then(literal("godfrog").executes(context -> {
					context.getSource().sendFeedback(Text.literal("On top of portal in shadow world"));
					return 0;
				}
			)
		)
		.then(literal("mocsek").executes(context -> {
					context.getSource().sendFeedback(Text.literal("On top of a stack of boxes in ikea world"));
					return 0;
				}
			)
		)
		.then(literal("palm").executes(context -> {
						context.getSource().sendFeedback(Text.literal("On top of oasis portal in portal room 3"));
						return 0;
				}
			)
		)
		));
	}
}