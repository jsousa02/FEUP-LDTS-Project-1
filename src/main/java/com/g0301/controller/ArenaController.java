package com.g0301.controller;

import com.g0301.Gui.Gui;
import com.g0301.model.*;
import com.g0301.state.KeyboardListener;

public class ArenaController extends GameController implements KeyboardListener {

    private final CarController carController;
    private final CarController botController;

    public ArenaController(Arena arena) {
        super(arena);
        this.carController = new CarController(arena.getCar());
        this.botController = new CarController(arena.getBot());
    }


    public void step(Gui.ACTION action) {
        Position currentPosition = carController.getCar().getPosition();
        Position nextPosition = carController.makeMovement(action);
        Position botCurrentPosition = botController.getCar().getPosition();
        Position botNextPosition = botController.botMovement();

        if (!botController.getCar().collisionWithOwnTrail() && !getModel().wallCollision()) {
            botController.getCar().getTrailList().add(new Trail(botCurrentPosition, "#FFFF00"));
            if(!getModel().enterPortalThroughExit(action) && !getModel().enterPortalThroughStart(action))
                botController.moveCar(botNextPosition);
        }

        if (!carController.getCar().collisionWithOwnTrail() && !getModel().wallCollision()) {
            carController.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            if(!getModel().enterPortalThroughExit(action) && !getModel().enterPortalThroughStart(action))
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
    public CarController getBotController(){
        return botController;
    }
}
