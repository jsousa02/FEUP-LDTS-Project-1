package com.g0301.viewer.state;

import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.model.Position;
import com.g0301.viewer.ButtonViewer;

import java.io.IOException;
import java.util.List;

public class GameOverViewer extends StateViewer {

    public GameOverViewer(Gui gui, List<Button> buttons) {
        super(gui, buttons);
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        drawTitle("#000000", "#FF0000");
        gui.drawText(gui.createTextGraphics(), new Position(gui.getWidth() / 2 - 4, 20), "#FF0000", "Game Over!");
        drawElements(buttons, new ButtonViewer());
        gui.refresh();
    }
}
