package potatoslayer56.macutils.client.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

public class TimersConfigScreen extends Screen {
  private final @Nullable Screen parent;

  public TimersConfigScreen(@Nullable Screen parent) {
    super(Component.translatable("screen.macutils.title"));
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
      guiScale = 4;
    }

    Checkbox fortuneTimersCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.fortune"),this.font)
      .pos(10, 36)
      .selected(Variables.toggles.get("fortune").get("enabled"))
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.fortune")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.toggles.get("fortune").replace("enabled", value);
      })
      .build();

    Checkbox slayerTimersCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.slayers"),this.font)
      .pos(10, 56)
      .selected(Variables.toggles.get("slayer").get("enabled"))
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.slayers")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.toggles.get("slayer").replace("enabled", value);
      })
      .build();

    Checkbox fryTimerCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.frytimer"),this.font)
      .pos(10, 76)
      .selected(Variables.toggles.get("fry").get("enabled"))
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.frytimer")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.toggles.get("fry").replace("enabled", value);
      })
      .build();


    Checkbox moltenBladeTimerCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.moltenbladetimer"),this.font)
      .pos(10, 96)
      .selected(Variables.toggles.get("moltenblade").get("enabled"))
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.moltenbladetimer")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.toggles.get("moltenblade").replace("enabled", value);
      })
      .build();

    Checkbox rngCopyCheckbox = Checkbox
      .builder(Component.translatable("config.macutils.screen.label.copyrng"),this.font)
      .pos(10, 116)
      .selected(Variables.toggles.get("copyrng").get("enabled"))
      .maxWidth(this.font.width(Component.translatable("config.macutils.screen.label.copyrng")) + 16)
      .onValueChange((checkbox, value) -> {
        Variables.toggles.get("copyrng").replace("enabled", value);
      })
      .build();

    Button backButton = Button.builder(Component.translatable("config.macutils.button.saveAndClose"), (button -> {
      Minecraft.getInstance().setScreen(parent);
    })).bounds(
      10,
      (int) (((double) windowHeight / guiScale) * 0.9),
      this.font.width(Component.translatable("config.macutils.button.saveAndClose")) + 16,
      this.font.lineHeight + 16
    ).build();

    this.addRenderableWidget(fortuneTimersCheckbox);
    this.addRenderableWidget(slayerTimersCheckbox);
    this.addRenderableWidget(fryTimerCheckbox);
    this.addRenderableWidget(moltenBladeTimerCheckbox);
    this.addRenderableWidget(rngCopyCheckbox);
    this.addRenderableWidget(backButton);
  }

  @Override
  public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    super.extractRenderState(graphics, mouseX, mouseY, a);

    graphics.text(this.font, Component.translatable("config.macutils.screen.title"), 10, 10, 0xFFFFFFFF, true);
  }
}
