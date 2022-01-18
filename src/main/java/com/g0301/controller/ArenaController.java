package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.model.*;
import com.g0301.state.KeyboardListener;
import com.g0301.viewer.ArenaViewer;

import java.io.IOException;

public class ArenaController extends GameController implements KeyboardListener {

    private final CarController carController;
    private final ArenaViewer arenaViewer;
    private final Gui gui;

    public ArenaController(Gui gui, Arena arena) {
        super(arena);
        this.carController = new CarController(arena.getCar());
        this.gui = gui;
        this.arenaViewer = new ArenaViewer(gui, arena);
    }


    public void step() throws IOException {
        arenaViewer.draw();
    }

    public void action(Gui.ACTION action) {
        Position currentPosition = carController.getCar().getPosition();
        Position nextPosition = carController.makeMovement(action);

        if (!carController.getCar().collisionWithOwnTrail()) {
            carController.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            carController.moveCar(nextPosition);
        }
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        action(action);
    }

    public CarController getCarController() {
        return carController;
    }
}
