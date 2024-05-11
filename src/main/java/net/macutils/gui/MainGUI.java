package net.macutils.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.fabric.api.util.TriState;
import net.macutils.MacUtils;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MainGUI extends LightweightGuiDescription {
    public MainGUI() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        WLabel title = new WLabel(Text.literal("MacUtils"), 0x000000);
        title.setDarkmodeColor(0xffffff);
        root.add(title, 0, 0, 2, 1);

        WSprite autoFeedIcon = new WSprite(new Identifier("minecraft:textures/item/golden_carrot.png"));
        root.add(autoFeedIcon, 0, 2, 1, 1);

        WToggleButton autoFeedButton = new WToggleButton(Text.literal("Auto Feed"));
        autoFeedButton.setToggle(MacUtils.AUTOFEED);
        autoFeedButton.setOnToggle(on -> {
            if (on.booleanValue() == true){
                MacUtils.AUTOFEED = true;
            }
            else if (on.booleanValue() == false){
                MacUtils.AUTOFEED = false;
            }
        });
        root.add(autoFeedButton, 2, 2, 4, 1);

        root.validate(this);
    }

    @Override
    public TriState isDarkMode() {
        return TriState.TRUE;
    }
}