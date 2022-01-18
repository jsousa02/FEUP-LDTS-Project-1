package com.g0301.viewer;

import com.g0301.gui.Gui;
import com.g0301.model.Button;

public class ButtonViewer implements ElementViewer<Button> {

    @Override
    public void drawElement(Button element, Gui gui) {
        gui.drawButton(element.getPosition(), element.getColor(), element.getTextColor(), element.getText(), element.getWidth(), element.getHeight());
    }
}
