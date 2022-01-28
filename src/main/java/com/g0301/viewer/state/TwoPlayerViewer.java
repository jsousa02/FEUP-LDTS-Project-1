package com.g0301.viewer.state;

import com.g0301.gui.Gui;
import com.g0301.model.TwoPlayerArena;
import com.g0301.model.Button;
import com.g0301.viewer.*;

import java.io.IOException;
import java.util.List;

public class TwoPlayerViewer extends StateViewer {

    private TwoPlayerArena twoPlayerArena;

    public TwoPlayerViewer(Gui gui, List<Button> buttons, TwoPlayerArena twoPlayerArena) {
        super(gui, buttons);
        this.twoPlayerArena = twoPlayerArena;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawBackground(gui.createTextGraphics(), "#000000");

        drawElements(twoPlayerArena.getWalls(), new WallViewer());
        drawElement(twoPlayerArena.getPlayer1(), new CarViewer());
        drawElement(twoPlayerArena.getPlayer2(), new CarViewer());
        drawElements(twoPlayerArena.getPlayer1().getTrailList(), new TrailViewer());
        drawElements(twoPlayerArena.getPlayer2().getTrailList(), new TrailViewer());
        drawElements(twoPlayerArena.getPortals(), new PortalViewer());
        drawElements(twoPlayerArena.getPlayer1BoostBar().getBoostBarTrails(), new Player1BoostBarViewer());
        drawElements(twoPlayerArena.getPlayer2BoostBar().getBoostBarTrails(), new Player2BoostBarViewer());

        gui.refresh();
    }
}
