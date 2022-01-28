package com.g0301.viewer;

import com.g0301.gui.Gui;
import com.g0301.model.Trail;

public class Player1BoostBarViewer implements ElementViewer<Trail> {

    @Override
    public void drawElement(Trail element, Gui gui) {
        gui.drawPlayer1BoostBar(element.getPosition(), element.getColor());
    }
}
