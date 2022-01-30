package com.g0301.viewer;

import com.g0301.gui.Gui;
import com.g0301.model.Wall;

public class WallViewer implements ElementViewer<Wall> {

    @Override
    public void drawElement(Wall element, Gui gui) {
        gui.drawWall(element.getPosition(), element.getColor());
    }
}
