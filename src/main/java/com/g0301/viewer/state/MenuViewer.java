package com.g0301.viewer.state;

import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.viewer.ButtonViewer;

import java.io.IOException;
import java.util.List;

public class MenuViewer extends StateViewer {

    public MenuViewer(Gui gui, List<Button> buttons) {
        super(gui, buttons);
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        drawBackground("#000000");
        drawTitle("#000000", "#FF0000");
        drawElements(buttons, new ButtonViewer());
        gui.refresh();
    }
}
