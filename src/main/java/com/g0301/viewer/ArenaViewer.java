package com.g0301.viewer;

import com.g0301.gui.Gui;
import com.g0301.model.Arena;
import com.g0301.model.Element;

import java.io.IOException;
import java.util.Collection;

public class ArenaViewer {
    private final Gui gui;
    private final Arena arena;

    public ArenaViewer(Gui gui, Arena arena) {
        this.gui = gui;
        this.arena = arena;
    }

    /**
     * @breif Draws all the elements in the arena
     */
    public void draw() throws IOException {
        gui.clear();
        gui.drawBackground(gui.createTextGraphics(), "#000000");

        drawElements(arena.getWalls(), new WallViewer());
        drawElement(arena.getCar(), new CarViewer());
        drawElement(arena.getBot(), new CarViewer());
        drawElements(arena.getCar().getTrailList(), new TrailViewer());
        drawElements(arena.getBot().getTrailList(), new TrailViewer());
        drawElements(arena.getPortals(), new PortalViewer());

        gui.refresh();
    }

    /**
     * @brief Handles multiple element drawing
     * @param elements The element to be drawn
     * @param viewer The viewer responsible for drawing the elements
     */
    private <T extends Element> void drawElements(Collection<T> elements, ElementViewer<T> viewer) {
        for (T element : elements) {
            drawElement(element, viewer);
        }
    }

    /**
     * @breif Handles single element drawing
     * @param element The element to be drawn
     * @param viewer The viewer responsible for drawing the element
     */
    private <T extends Element> void drawElement(T element, ElementViewer<T> viewer) {
        viewer.drawElement(element, gui);
    }
}
