package net.macutils.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;

/**
 * This subclass doesn't need to do anything, just be a distinct
 * class so that anyone making edits or adding buttons can find us
 * with an instanceof check
 */
public class MainScreen extends CottonClientScreen {
    public MainScreen(GuiDescription description) {
        super(description);
    }
}