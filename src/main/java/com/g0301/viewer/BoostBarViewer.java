package com.g0301.viewer;

import com.g0301.gui.Gui;
import com.g0301.model.Trail;

public class BoostBarViewer implements ElementViewer<Trail> {

    @Override
    public void drawElement(Trail element, Gui gui) {
        gui.drawBoostBar(element.getPosition(), element.getColor());
    }
}
