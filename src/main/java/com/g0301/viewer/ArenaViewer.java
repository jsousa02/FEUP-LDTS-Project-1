package com.g0301.viewer;

import com.g0301.Gui.Gui;
import com.g0301.model.Arena;
import com.g0301.model.Element;

import java.io.IOException;
import java.util.List;

public class ArenaViewer {
    private final Gui gui;
    private final Arena arena;

    public ArenaViewer(Gui gui, Arena arena) {
        this.gui = gui;
        this.arena = arena;
    }

    public void draw() throws IOException {
        gui.clear();
        gui.drawBackground(gui.createTextGraphics(), "#000000");

        drawElements(arena.getWalls(), new WallViewer());
        drawElement(arena.getCar(), new CarViewer());
        drawElements(arena.getCar().getTrailList(), new TrailViewer());

        gui.refresh();
    }

    private <T extends Element> void drawElements(List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements) {
            drawElement(element, viewer);
        }
    }

    private <T extends Element> void drawElement(T element, ElementViewer<T> viewer) {
        viewer.drawElement(element, gui);
    }
}
