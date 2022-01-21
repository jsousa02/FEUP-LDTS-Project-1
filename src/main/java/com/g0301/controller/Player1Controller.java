package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.model.Car;
import com.g0301.model.Position;

public class Player1Controller extends CarController {

    public Player1Controller(Car car) {
        super(car);
    }

    /**
     * @return The position above the current one
     */
    public Position moveUp() {
        getCar().setPreviousMovement(Gui.ACTION.UP);
        return getCar().getPosition().moveUp();
    }

    /**
     * @return The position below the current  one
     */
    public Position moveDown() {
        getCar().setPreviousMovement(Gui.ACTION.DOWN);
        return getCar().getPosition().moveDown();
    }

    /**
     * @return The position to the left of the current one
     */
    public Position moveLeft() {
        getCar().setPreviousMovement(Gui.ACTION.LEFT);
        return getCar().getPosition().moveLeft();
    }

    /**
     * @return The position to the right of the current one
     */
    public Position moveRight() {
        getCar().setPreviousMovement(Gui.ACTION.RIGHT);
        return getCar().getPosition().moveRight();
    }

    /**
     * @param action The movement made by the player
     * @return The position corresponding to the movement
     * @briefs Handles the movements chosen by the player
     */
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

    /**
     * @param position The position to which the car is moved
     * @brief Moves the car to a new position
     */
    public void moveCar(Position position) {
        getCar().setPosition(position);
    }
}
