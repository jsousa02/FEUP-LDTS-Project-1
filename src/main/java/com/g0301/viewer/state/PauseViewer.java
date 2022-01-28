package com.g0301.viewer.state;

import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.model.Position;
import com.g0301.viewer.ButtonViewer;

import java.io.IOException;
import java.util.List;

public class PauseViewer extends StateViewer {

    public PauseViewer(Gui gui, List<Button> buttons) {
        super(gui, buttons);
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        drawTitle("#000000", "#FF0000");
        gui.drawText(gui.createTextGraphics(), new Position(gui.getWidth() / 2 - 2, 20), "#FFFFFF", "PAUSE");
        drawElements(buttons, new ButtonViewer());
        gui.refresh();
    }
}
