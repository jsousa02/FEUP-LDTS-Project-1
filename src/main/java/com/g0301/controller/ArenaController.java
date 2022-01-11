package com.g0301.controller;

import com.g0301.Gui.Gui;
import com.g0301.model.Arena;
import com.g0301.model.Position;
import com.g0301.state.KeyboardListener;

public class ArenaController extends GameController implements KeyboardListener {

    private final CarController carController;

    public ArenaController(Arena arena) {
        super(arena);
        this.carController = new CarController(arena.getCar());
    }

    public void makeMovement(Gui.ACTION action) {
        Position nextPosition = carController.makeMovement(action);
        System.out.println(nextPosition.getX());
        System.out.println(nextPosition.getY());
        carController.moveCar(nextPosition);
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        makeMovement(action);
    }
}
