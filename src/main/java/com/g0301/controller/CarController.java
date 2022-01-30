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

    public abstract Position moveUp();

    public abstract Position moveDown();

    public abstract Position moveLeft();

    public abstract Position moveRight();

    public abstract Position makeMovement(Gui.ACTION action);

    public abstract void moveCar(Position position);
}
