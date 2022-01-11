package com.g0301.viewer;

import com.g0301.Gui.Gui;
import com.g0301.model.Wall;

public class WallViewer implements ElementViewer<Wall> {

    /**
     * @brief Handles wall drawing
     * @param element The wall to be drawn
     * @param gui The GUI in which the wall is drawn
     */
    @Override
    public void drawElement(Wall element, Gui gui) {
        gui.drawWall(element.getPosition(), element.getColor());
    }
}
