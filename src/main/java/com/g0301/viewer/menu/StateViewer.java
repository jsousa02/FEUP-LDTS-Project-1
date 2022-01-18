package com.g0301.viewer.menu;

import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.viewer.ElementViewer;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

public abstract class StateViewer {
    protected final Gui gui;
    private final TextGraphics graphics;
    protected final List<Button> buttons;

    public StateViewer(Gui gui, List<Button> buttons) {
        this.gui = gui;
        this.graphics = gui.createTextGraphics();
        this.buttons = buttons;
    }

    public abstract void draw() throws IOException;

    protected void drawButtons(List<Button> buttons, ElementViewer<Button> viewer) {
        for (int i = 0; i < buttons.size(); i++) {
            viewer.drawElement(buttons.get(i), gui);
        }
    }

    protected void drawBackground(String color) {
        gui.drawBackground(graphics, color);
    }

    protected void drawTitle(String color, String textColor) {
        gui.drawLogo(color, textColor);
    }
}
