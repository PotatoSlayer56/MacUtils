package potatoslayer56.macutils.commands;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static potatoslayer56.macutils.MacUtils.AUTOFEED;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class autofeed {
    public static void register(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.
            register(literal("autofeed").executes(context -> {
                AUTOFEED = !AUTOFEED;
                if(AUTOFEED){
                    MinecraftClient.getInstance().player.sendMessage(Text.literal("§aAutofeed Enabled"));
                }
                else{
                    MinecraftClient.getInstance().player.sendMessage(Text.literal("§cAutofeed Disabled"));
                }
                return 0;
            }))));
    }
}
