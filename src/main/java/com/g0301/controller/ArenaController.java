package com.g0301.controller;

import com.g0301.Gui.Gui;
import com.g0301.model.*;
import com.g0301.state.KeyboardListener;

public class ArenaController extends GameController implements KeyboardListener {

    private final CarController carController;

    public ArenaController(Arena arena) {
        super(arena);
        this.carController = new CarController(arena.getCar());
    }


    public void step(Gui.ACTION action) {
        Position currentPosition = carController.getCar().getPosition();
        Position nextPosition = carController.makeMovement(action);

        if (!carController.getCar().collisionWithOwnTrail() && !getModel().wallCollision()) {
            carController.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            carController.moveCar(nextPosition);
        }
    }

    @Override
    public void keyReleased(Gui.ACTION action) {
            step(action);
        }

    public CarController getCarController() {
        return carController;
    }
}
