package com.g0301.viewer;

import com.g0301.gui.Gui;
import com.g0301.model.Trail;

public class Player2BoostBarViewer implements ElementViewer<Trail> {

    @Override
    public void drawElement(Trail element, Gui gui) {
        gui.drawPlayer2BoostBar(element.getPosition(), element.getColor());
    }
}
