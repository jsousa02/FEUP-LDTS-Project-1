package com.g0301.viewer.state;

import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.model.SurvivalArena;
import com.g0301.viewer.*;

import java.io.IOException;
import java.util.List;

public class SurvivalViewer extends StateViewer {

    private final SurvivalArena SurvivalArena;

    public SurvivalViewer(Gui gui, List<Button> buttons, SurvivalArena SurvivalArena) {
        super(gui, buttons);
        this.SurvivalArena = SurvivalArena;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawBackground(gui.createTextGraphics(), "#000000");

        drawElements(SurvivalArena.getWalls(), new WallViewer());
        drawElement(SurvivalArena.getPlayer1(), new CarViewer());
        drawElement(SurvivalArena.getBot(), new CarViewer());
        drawElements(SurvivalArena.getPlayer1().getTrailList(), new TrailViewer());
        drawElements(SurvivalArena.getBot().getTrailList(), new TrailViewer());
        drawElements(SurvivalArena.getPortals(), new PortalViewer());
        gui.drawScore(SurvivalArena.getScore());
        gui.refresh();
    }
}
