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
    private Gui.ACTION movement = Gui.ACTION.RIGHT;

    public ArenaController(Gui gui, Arena arena) {
        super(arena);
        this.carController = new CarController(arena.getCar());
        this.gui = gui;
        this.arenaViewer = new ArenaViewer(gui, arena);
    }


    public void step() throws IOException {
        arenaViewer.draw();
        action(movement);
    }

    public void action(Gui.ACTION action) {
        Position currentPosition = carController.getCar().getPosition();
        Position nextPosition = carController.makeMovement(movement);

        if (!carController.getCar().collisionWithOwnTrail() && !getModel().wallCollision() && !getModel().outOfBounds()) {
            carController.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            if(!getModel().enterPortalThroughExit(action) && !getModel().enterPortalThroughStart(action))
                if(getModel().getBoostBar().isActive()) {
                    carController.getCar().changeSpeed(2);
                    carController.moveCar(nextPosition);
                }
                else {
                    carController.getCar().changeSpeed(1);
                    carController.moveCar(nextPosition);
                }
        }
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        if(action == Gui.ACTION.P1BOOST) {
            getModel().getBoostBar().setHoldTime(getModel().getBoostBar().getHoldTime() + 1);
            if(getModel().getBoostBar().isEmpty())
                getModel().getBoostBar().deactivate();
            else
                getModel().getBoostBar().activate();

            if(getModel().getBoostBar().getHoldTime() > 10) {
                getModel().getBoostBar().decrease();
            }
        }
        else {
            getModel().getBoostBar().setReleaseTime(getModel().getBoostBar().getReleaseTime() + 1);
            getModel().getBoostBar().deactivate();

            if(getModel().getBoostBar().getReleaseTime() > 4) {
                getModel().getBoostBar().increase();
            }
            movement = action;
        }
    }

    public CarController getCarController() {
        return carController;
    }
}
