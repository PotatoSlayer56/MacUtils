package potatoslayer56.macutils.client.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

public class CommandsConfigScreen extends Screen {
  private final @Nullable Screen parent;

  public CommandsConfigScreen(@Nullable Screen parent) {
    super(Component.translatable("screen.macutils.title"));
    this.parent = parent;
  }

  @Override
  public void onClose() {
    Minecraft.getInstance().setScreen(parent);
  }
}
