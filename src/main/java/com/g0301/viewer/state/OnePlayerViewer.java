package com.g0301.viewer.state;

import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.model.OnePlayerArena;
import com.g0301.viewer.*;

import java.io.IOException;
import java.util.List;

public class OnePlayerViewer extends StateViewer {

    private final OnePlayerArena onePlayerArena;

    public OnePlayerViewer(Gui gui, List<Button> buttons, OnePlayerArena onePlayerArena) {
        super(gui, buttons);
        this.onePlayerArena = onePlayerArena;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        gui.drawBackground(gui.createTextGraphics(), "#000000");

        drawElements(onePlayerArena.getWalls(), new WallViewer());
        drawElement(onePlayerArena.getPlayer1(), new CarViewer());
        drawElement(onePlayerArena.getBot(), new CarViewer());
        drawElements(onePlayerArena.getPlayer1().getTrailList(), new TrailViewer());
        drawElements(onePlayerArena.getBot().getTrailList(), new TrailViewer());
        drawElements(onePlayerArena.getPortals(), new PortalViewer());
        drawElements(onePlayerArena.getBoostBar().getBoostBarTrails(), new Player1BoostBarViewer());

        gui.refresh();
    }
}
