package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.model.Bot;
import com.g0301.model.Position;

import java.util.Random;

public class BotController extends CarController {

    public BotController(Bot bot) {
        super(bot);
    }

    @Override
    public Position moveUp() {
        return getCar().getPosition().moveUp(1);
    }

    @Override
    public Position moveDown() {
        return getCar().getPosition().moveDown(1);
    }

    @Override
    public Position moveLeft() {
        return getCar().getPosition().moveLeft(1);
    }

    @Override
    public Position moveRight() {
        return getCar().getPosition().moveRight(1);
    }

    @Override
    public Position makeMovement(Gui.ACTION action) {
        if (action == Gui.ACTION.UP) {
            if (getCar().getPreviousMovement() != Gui.ACTION.DOWN) {
                return moveUp();
            } else return makeMovement(getCar().getPreviousMovement());
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

    @Override
    public void moveCar(Position position) {
        getCar().setPosition(position);
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
