package com.g0301.viewer;

import com.g0301.gui.Gui;
import com.g0301.model.Trail;

public class TrailViewer implements ElementViewer<Trail> {

    /**
     * @brief Handles trail drawing
     * @param element The trail to be drawn
     * @param gui The GUI in which the trail is drawn
     */
    @Override
    public void drawElement(Trail element, Gui gui) {
        gui.drawTrail(element.getPosition(), element.getColor());
    }
}
