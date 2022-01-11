package com.g0301.model;

public abstract class Element {
    protected Position position;
    protected String color;

    public Element(Position position, String color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String newColor) {
        this.color = newColor;
    }
}
