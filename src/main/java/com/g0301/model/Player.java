package com.g0301.model;

public class Player extends Car{

    private int speed;

    public Player(Position position, String color) {
        super(position, color);
        speed = 1;
    }
}
