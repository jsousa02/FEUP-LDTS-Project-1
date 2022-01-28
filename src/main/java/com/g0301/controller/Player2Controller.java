package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.model.Car;
import com.g0301.model.Position;

public class Player2Controller extends CarController {

    public Player2Controller(Car car) {
        super(car);
    }

    public Position moveUp() {
        getCar().setPreviousMovement(Gui.ACTION.P2UP);
        return getCar().getPosition().moveUp(getCar().getSpeed());
    }

    public Position moveDown() {
        getCar().setPreviousMovement(Gui.ACTION.P2DOWN);
        return getCar().getPosition().moveDown(getCar().getSpeed());
    }

    public Position moveLeft() {
        getCar().setPreviousMovement(Gui.ACTION.P2LEFT);
        return getCar().getPosition().moveLeft(getCar().getSpeed());
    }

    public Position moveRight() {
        getCar().setPreviousMovement(Gui.ACTION.P2RIGHT);
        return getCar().getPosition().moveRight(getCar().getSpeed());
    }

    public Position makeMovement(Gui.ACTION action) {
        if (action == Gui.ACTION.P2UP){
            if (getCar().getPreviousMovement()!=Gui.ACTION.P2DOWN){
                return moveUp();
            }
            else return makeMovement(getCar().getPreviousMovement());
        }
        if (action == Gui.ACTION.P2LEFT) {
            if (getCar().getPreviousMovement() != Gui.ACTION.P2RIGHT) {
                return moveLeft();
            } else return makeMovement(getCar().getPreviousMovement());
        }
        if (action == Gui.ACTION.P2DOWN) {
            if (getCar().getPreviousMovement() != Gui.ACTION.P2UP) {
                return moveDown();
            } else return makeMovement(getCar().getPreviousMovement());
        }
        if (action == Gui.ACTION.P2RIGHT) {
            if (getCar().getPreviousMovement() != Gui.ACTION.P2LEFT) {
                return moveRight();
            } else return makeMovement(getCar().getPreviousMovement());
        }
        return null;
    }

    public void moveCar(Position position) {
        getCar().setPosition(position);
    }
}
