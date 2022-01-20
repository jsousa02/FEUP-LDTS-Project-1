package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.model.Car;
import com.g0301.model.Position;

import java.util.Random;

public class CarController {
    private Car car;

    public CarController(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    /**
     * @return The position above the current one
     */
    public Position moveUp() {
        car.setPreviousMovement(Gui.ACTION.UP);
        return car.getPosition().moveUp();
    }

    /**
     * @return The position below the current  one
     */
    public Position moveDown() {
        car.setPreviousMovement(Gui.ACTION.DOWN);
        return car.getPosition().moveDown();
    }

    /**
     * @return The position to the left of the current one
     */
    public Position moveLeft() {
        car.setPreviousMovement(Gui.ACTION.LEFT);
        return car.getPosition().moveLeft();
    }

    /**
     * @return The position to the right of the current one
     */
    public Position moveRight() {
        car.setPreviousMovement(Gui.ACTION.RIGHT);
        return car.getPosition().moveRight();
    }

    /**
     * @param action The movement made by the player
     * @return The position corresponding to the movement
     * @briefs Handles the movements chosen by the player
     */
    public Position makeMovement(Gui.ACTION action) {
        if (action == Gui.ACTION.UP) return moveUp();
        if (action == Gui.ACTION.LEFT) return moveLeft();
        if (action == Gui.ACTION.DOWN) return moveDown();
        if (action == Gui.ACTION.RIGHT) return moveRight();
        return null;
    }

    /**
     * @param position The position to which the car is moved
     * @brief Moves the car to a new position
     */
    public void moveCar(Position position) {
        car.setPosition(position);
    }

    public Position botMovement() {
        int rand = new Random().nextInt(4);
        if (rand == 0) {
            Gui.ACTION action = Gui.ACTION.LEFT;
           return makeMovement(action);
        } else if (rand == 1) {
            Gui.ACTION action = Gui.ACTION.RIGHT;
            return makeMovement(action);
        } else if (rand == 2) {
            Gui.ACTION action = Gui.ACTION.UP;
            return makeMovement(action);
        }
            Gui.ACTION action = Gui.ACTION.DOWN;
            return makeMovement(action);
        }
    }
