package com.g0301.model;

public class Portal extends Element {

    public Portal(Position startPortal, Position exitPortal, String color) {
        super(startPortal, exitPortal, color);
    }

    public String getColor() {
        return color;
    }

}
