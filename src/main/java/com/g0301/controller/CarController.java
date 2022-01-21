package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.model.Car;
import com.g0301.model.Position;

public abstract class CarController {
    private Car car;

    public CarController(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car){
        this.car = car;
    }
    /**
     * @return The position above the current one
     */
    public abstract Position moveUp();
    /**
     * @return The position below the current  one
     */
    public abstract Position moveDown();

    /**
     * @return The position to the left of the current one
     */
    public abstract Position moveLeft();

    /**
     * @return The position to the right of the current one
     */
    public abstract Position moveRight();
    /**
     * @param action The movement made by the player
     * @return The position corresponding to the movement
     * @briefs Handles the movements chosen by the player
     */
    public abstract Position makeMovement(Gui.ACTION action);

    /**
     * @param position The position to which the car is moved
     * @brief Moves the car to a new position
     */
    public abstract void moveCar(Position position);
}
