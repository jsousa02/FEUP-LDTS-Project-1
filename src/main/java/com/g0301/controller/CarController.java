package com.g0301.controller;

import com.g0301.Gui.Gui;
import com.g0301.model.Car;
import com.g0301.model.Position;

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
        return car.getPosition().moveUp();
    }

    /**
     * @return The position below the current  one
     */
    public Position moveDown() {
        return car.getPosition().moveDown();
    }

    /**
     * @return The position to the left of the current one
     */
    public Position moveLeft() {
        return car.getPosition().moveLeft();
    }

    /**
     * @return The position to the right of the current one
     */
    public Position moveRight() {
        return car.getPosition().moveRight();
    }

    /**
     * @briefs Handles the movements chosen by the player
     * @param action The movement made by the player
     * @return The position corresponding to the movement
     */
    public Position makeMovement(Gui.ACTION action) {
        if(action == Gui.ACTION.UP) return moveUp();
        if(action == Gui.ACTION.LEFT) return moveLeft();
        if(action == Gui.ACTION.DOWN) return moveDown();
        if(action == Gui.ACTION.RIGHT) return moveRight();
        return null;
    }

    /**
     * @brief Moves the car to a new position
     * @param position The position to which the car is moved
     */
    public void moveCar(Position position) {
        car.setPosition(position);
    }
}