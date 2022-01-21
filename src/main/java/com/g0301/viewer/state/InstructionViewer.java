package com.g0301.viewer.state;

import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.viewer.ButtonViewer;

import java.io.IOException;
import java.util.List;

public class InstructionViewer extends StateViewer {

    public InstructionViewer(Gui gui, List<Button> buttons) {
        super(gui, buttons);
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawInstructions();
        drawElements(buttons, new ButtonViewer());
        gui.refresh();
    }
}
