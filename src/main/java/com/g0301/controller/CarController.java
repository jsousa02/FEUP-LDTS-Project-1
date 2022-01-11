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

    public Position moveUp() {
        return car.getPosition().moveUp();
    }

    public Position moveDown() {
        return car.getPosition().moveDown();
    }

    public Position moveLeft() {
        return car.getPosition().moveLeft();
    }

    public Position moveRight() {
        return car.getPosition().moveRight();
    }

    public Position makeMovement(Gui.ACTION action) {
        if(action == Gui.ACTION.UP) return moveUp();
        if(action == Gui.ACTION.LEFT) return moveLeft();
        if(action == Gui.ACTION.DOWN) return moveDown();
        if(action == Gui.ACTION.RIGHT) return moveRight();
        return null;
    }

    public void moveCar(Position position) {
        car.setPosition(position);
    }
}