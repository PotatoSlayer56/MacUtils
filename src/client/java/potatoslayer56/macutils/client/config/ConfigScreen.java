package potatoslayer56.macutils.client.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;
import org.jspecify.annotations.Nullable;

public class ConfigScreen extends Screen {
  private final @Nullable Screen parent;

  public ConfigScreen(@Nullable Screen parent) {
    super(Component.translatable("screen.title.main"));
    this.parent = parent;
  }

  @Override
  protected void init() {
    EditBox customCommand1Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.screen.label.custom-command1")) + 15,
      36,
      Minecraft.getInstance().getWindow().getWidth() / (4 * Minecraft.getInstance().options.guiScale().get()),
      16,
      Component.translatable("config.screen.placeholder.custom-command1")
    );

    EditBox customCommand2Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.screen.label.custom-command2")) + 15,
      56,
      Minecraft.getInstance().getWindow().getWidth() / (4 * Minecraft.getInstance().options.guiScale().get()),
      16,
      Component.translatable("config.screen.placeholder.custom-command2")
    );

    EditBox customCommand3Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.screen.label.custom-command3")) + 15,
      76,
      Minecraft.getInstance().getWindow().getWidth() / (4 * Minecraft.getInstance().options.guiScale().get()),
      16,
      Component.translatable("config.screen.placeholder.custom-command3")
    );

    EditBox customCommand4Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.screen.label.custom-command4")) + 15,
      96,
      Minecraft.getInstance().getWindow().getWidth() / (4 * Minecraft.getInstance().options.guiScale().get()),
      16,
      Component.translatable("config.screen.placeholder.custom-command4")
    );

    EditBox customCommand5Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.screen.label.custom-command5")) + 15,
      116,
      Minecraft.getInstance().getWindow().getWidth() / (4 * Minecraft.getInstance().options.guiScale().get()),
      16,
      Component.translatable("config.screen.placeholder.custom-command5")
    );

    customCommand1Input.setValue(Variables.customCommands.get("customCommand1"));
    customCommand2Input.setValue(Variables.customCommands.get("customCommand2"));
    customCommand3Input.setValue(Variables.customCommands.get("customCommand3"));
    customCommand4Input.setValue(Variables.customCommands.get("customCommand4"));
    customCommand5Input.setValue(Variables.customCommands.get("customCommand5"));

    Button saveAndCloseButton = Button.builder(Component.translatable("config.button.saveAndClose"), (button -> {
      Variables.customCommands.replace("customCommand1", customCommand1Input.getValue());
      Variables.customCommands.replace("customCommand2", customCommand2Input.getValue());
      Variables.customCommands.replace("customCommand3", customCommand3Input.getValue());
      Variables.customCommands.replace("customCommand4", customCommand4Input.getValue());
      Variables.customCommands.replace("customCommand5", customCommand5Input.getValue());
      SaveLoad.saveConfig();
      Minecraft.getInstance().setScreen(null);
    })).bounds(
      10,
      (int) (((double) Minecraft.getInstance().getWindow().getHeight() / Minecraft.getInstance().options.guiScale().get()) * 0.9),
      this.font.width(Component.translatable("config.button.saveAndClose")) + 16,
      this.font.lineHeight + 16
    ).build();

    this.addRenderableWidget(customCommand1Input);
    this.addRenderableWidget(customCommand2Input);
    this.addRenderableWidget(customCommand3Input);
    this.addRenderableWidget(customCommand4Input);
    this.addRenderableWidget(customCommand5Input);
    this.addRenderableWidget(saveAndCloseButton);
  }

  @Override
  public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    super.extractRenderState(graphics, mouseX, mouseY, a);

    graphics.text(this.font, Component.translatable("config.screen.title"), 10, 10, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.screen.label.custom-command1"), 10, 40, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.screen.label.custom-command2"), 10, 60, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.screen.label.custom-command3"), 10, 80, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.screen.label.custom-command4"), 10, 100, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.screen.label.custom-command5"), 10, 120, 0xFFFFFFFF, true);
  }

  @Override
  public void onClose() {
    SaveLoad.saveConfig();
    Minecraft.getInstance().setScreen(parent);
  }
}
