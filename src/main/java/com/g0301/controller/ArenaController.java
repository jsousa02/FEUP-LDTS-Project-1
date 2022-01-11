package com.g0301.controller;

import com.g0301.Gui.Gui;
import com.g0301.model.Arena;
import com.g0301.model.Position;
import com.g0301.model.Trail;
import com.g0301.state.KeyboardListener;

public class ArenaController extends GameController implements KeyboardListener {

    private final CarController carController;

    public ArenaController(Arena arena) {
        super(arena);
        this.carController = new CarController(arena.getCar());
    }

    /**
     * @brief Checks if the player movement is valid
     * @param action The movement made by the player
     */
    public void makeMovement(Gui.ACTION action) {
        Position currentPosition = carController.getCar().getPosition();
        Position nextPosition = carController.makeMovement(action);

        if (!carController.getCar().collisionWithOwnTrail()) {
            carController.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            carController.moveCar(nextPosition);
        }
    }

    /**
     * @brief Makes the player move
     * @param action The move made by the player
     */
    @Override
    public void keyPressed(Gui.ACTION action) {
        makeMovement(action);
    }
}
