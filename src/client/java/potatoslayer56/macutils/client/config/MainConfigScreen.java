package potatoslayer56.macutils.client.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

public class MainConfigScreen extends Screen {
  private final @Nullable Screen parent;

  public MainConfigScreen(@Nullable Screen parent) {
    super(Component.translatable("screen.macutils.title"));
    this.parent = parent;
  }

  @Override
  protected void init() {
    Button timersConfigButton = Button.builder(Component.translatable("config.macutils.button.moduleconfig"), button -> {
      Minecraft.getInstance().setScreen(new ModulesConfigScreen(this));
    }).bounds(
      10,
      50,
      this.font.width(Component.translatable("config.macutils.button.moduleconfig")) + 16,
      this.font.lineHeight + 16
    ).build();

    Button commandsConfigButton = Button.builder(Component.translatable("config.macutils.button.commandconfig"), button -> {
      Minecraft.getInstance().setScreen(new CommandsConfigScreen(this));
    }).bounds(
      10,
      70 + this.font.lineHeight + 16,
      this.font.width(Component.translatable("config.macutils.button.commandconfig")) + 16,
      this.font.lineHeight + 16
    ).build();

    this.addRenderableWidget(timersConfigButton);
    this.addRenderableWidget(commandsConfigButton);
  }

  @Override
  public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    graphics.text(this.font,Component.translatable("screen.macutils.title"), 10, 10, 0xFFFFFFFF, false);
  }

  @Override
  public void onClose() {
    SaveLoad.saveConfig();
    Minecraft.getInstance().setScreen(parent);
  }
}
