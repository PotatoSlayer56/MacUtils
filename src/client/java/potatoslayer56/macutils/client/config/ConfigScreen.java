package potatoslayer56.macutils.client.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;
import org.jspecify.annotations.Nullable;

public class ConfigScreen extends Screen {
  private final @Nullable Screen parent;

  public ConfigScreen(@Nullable Screen parent) {
    super(Component.translatable("screen.macutils.title.main"));
    this.parent = parent;
  }

  @Override
  protected void init() {
    int guiScale;
    int windowWidth = Minecraft.getInstance().getWindow().getWidth();
    int windowHeight = Minecraft.getInstance().getWindow().getHeight();

    if(Minecraft.getInstance().options.guiScale().get() != 0){
      guiScale = Minecraft.getInstance().options.guiScale().get();
    }
    else {
      guiScale = 1;
    }

    EditBox customCommand1Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.macutils.screen.label.custom-command1")) + 15,
      36,
      windowWidth / (4 * guiScale),
      16,
      Component.translatable("config.macutils.screen.placeholder.custom-command1")
    );

    EditBox customCommand2Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.macutils.screen.label.custom-command2")) + 15,
      56,
      windowWidth / (4 * guiScale),
      16,
      Component.translatable("config.macutils.screen.placeholder.custom-command2")
    );

    EditBox customCommand3Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.macutils.screen.label.custom-command3")) + 15,
      76,
      windowWidth / (4 * guiScale),
      16,
      Component.translatable("config.macutils.screen.placeholder.custom-command3")
    );

    EditBox customCommand4Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.macutils.screen.label.custom-command4")) + 15,
      96,
      windowWidth / (4 * guiScale),
      16,
      Component.translatable("config.macutils.screen.placeholder.custom-command4")
    );

    EditBox customCommand5Input = new EditBox(
      this.font,
      this.font.width(Component.translatable("config.macutils.screen.label.custom-command5")) + 15,
      116,
      windowWidth / (4 * guiScale),
      16,
      Component.translatable("config.macutils.screen.placeholder.custom-command5")
    );

    Checkbox fortuneTimersCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.fortune"),this.font)
      .pos(windowWidth / (2 * guiScale), 36)
      .selected(Variables.FORTUNETIMERSENABLED)
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.fortune")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.FORTUNETIMERSENABLED = value;
      })
      .build();

    Checkbox slayerTimersCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.slayers"),this.font)
      .pos(windowWidth / (2 * guiScale), 56)
      .selected(Variables.SLAYERTIMERSENABLED)
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.slayers")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.SLAYERTIMERSENABLED = value;
      })
      .build();

    Checkbox fryTimerCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.frytimer"),this.font)
      .pos(windowWidth / (2 * guiScale), 76)
      .selected(Variables.FRYTIMERENBALED)
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.frytimer")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.FRYTIMERENBALED = value;
      })
      .build();


    Checkbox moltenBladeTimerCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.moltenbladetimer"),this.font)
      .pos(windowWidth / (2 * guiScale), 96)
      .selected(Variables.MOLTENBLADETIMERENABLED)
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.moltenbladetimer")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.MOLTENBLADETIMERENABLED = value;
      })
      .build();

    Checkbox rngCopyCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.copyrng"),this.font)
      .pos(windowWidth / (2 * guiScale), 116)
      .selected(Variables.COPYRNGMESSAGESENABLED)
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.copyrng")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.COPYRNGMESSAGESENABLED = value;
      })
      .build();

    customCommand1Input.setValue(Variables.customCommands.get("customCommand1"));
    customCommand2Input.setValue(Variables.customCommands.get("customCommand2"));
    customCommand3Input.setValue(Variables.customCommands.get("customCommand3"));
    customCommand4Input.setValue(Variables.customCommands.get("customCommand4"));
    customCommand5Input.setValue(Variables.customCommands.get("customCommand5"));

    Button saveAndCloseButton = Button.builder(Component.translatable("config.macutils.button.saveAndClose"), (button -> {
      Variables.customCommands.replace("customCommand1", customCommand1Input.getValue());
      Variables.customCommands.replace("customCommand2", customCommand2Input.getValue());
      Variables.customCommands.replace("customCommand3", customCommand3Input.getValue());
      Variables.customCommands.replace("customCommand4", customCommand4Input.getValue());
      Variables.customCommands.replace("customCommand5", customCommand5Input.getValue());
      SaveLoad.saveConfig();
      Minecraft.getInstance().setScreen(null);
    })).bounds(
      10,
      (int) (((double) windowHeight / guiScale) * 0.9),
      this.font.width(Component.translatable("config.macutils.button.saveAndClose")) + 16,
      this.font.lineHeight + 16
    ).build();

    this.addRenderableWidget(customCommand1Input);
    this.addRenderableWidget(customCommand2Input);
    this.addRenderableWidget(customCommand3Input);
    this.addRenderableWidget(customCommand4Input);
    this.addRenderableWidget(customCommand5Input);
    this.addRenderableWidget(fortuneTimersCheckbox);
    this.addRenderableWidget(slayerTimersCheckbox);
    this.addRenderableWidget(fryTimerCheckbox);
    this.addRenderableWidget(moltenBladeTimerCheckbox);
    //this.addRenderableWidget(rngCopyCheckbox);
    this.addRenderableWidget(saveAndCloseButton);
  }

  @Override
  public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    super.extractRenderState(graphics, mouseX, mouseY, a);

    graphics.text(this.font, Component.translatable("config.macutils.screen.title"), 10, 10, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.macutils.screen.label.custom-command1"), 10, 40, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.macutils.screen.label.custom-command2"), 10, 60, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.macutils.screen.label.custom-command3"), 10, 80, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.macutils.screen.label.custom-command4"), 10, 100, 0xFFFFFFFF, true);
    graphics.text(this.font, Component.translatable("config.macutils.screen.label.custom-command5"), 10, 120, 0xFFFFFFFF, true);
  }

  @Override
  public void onClose() {
    SaveLoad.saveConfig();
    Minecraft.getInstance().setScreen(parent);
  }
}
