package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.model.Player;
import com.g0301.model.Position;

public class Player1Controller extends CarController {

    private int speed;

    public Player1Controller(Player car) {
        super(car);
        speed = 1;
    }

    public Position moveUp() {
        getCar().setPreviousMovement(Gui.ACTION.UP);
        return getCar().getPosition().moveUp(getCar().getSpeed());
    }

    public Position moveDown() {
        getCar().setPreviousMovement(Gui.ACTION.DOWN);
        return getCar().getPosition().moveDown(getCar().getSpeed());
    }

    public Position moveLeft() {
        getCar().setPreviousMovement(Gui.ACTION.LEFT);
        return getCar().getPosition().moveLeft(getCar().getSpeed());
    }

    public Position moveRight() {
        getCar().setPreviousMovement(Gui.ACTION.RIGHT);
        return getCar().getPosition().moveRight(getCar().getSpeed());
    }

    public Position makeMovement(Gui.ACTION action) {
        if (action == Gui.ACTION.UP){
            if (getCar().getPreviousMovement()!=Gui.ACTION.DOWN){
                return moveUp();
            }
            else return makeMovement(getCar().getPreviousMovement());
        }
        if (action == Gui.ACTION.LEFT) {
            if (getCar().getPreviousMovement() != Gui.ACTION.RIGHT) {
                return moveLeft();
            } else return makeMovement(getCar().getPreviousMovement());
        }
        if (action == Gui.ACTION.DOWN) {
            if (getCar().getPreviousMovement() != Gui.ACTION.UP) {
                return moveDown();
            } else return makeMovement(getCar().getPreviousMovement());
        }
        if (action == Gui.ACTION.RIGHT) {
            if (getCar().getPreviousMovement() != Gui.ACTION.LEFT) {
                return moveRight();
            } else return makeMovement(getCar().getPreviousMovement());
        }
        return null;
    }

    public void moveCar(Position position) {
        getCar().setPosition(position);
    }
}
