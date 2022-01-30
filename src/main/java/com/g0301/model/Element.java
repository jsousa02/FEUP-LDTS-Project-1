package com.g0301.model;

public abstract class Element {
    protected Position position;
    protected Position secondPosition;
    protected String color;

    public Element(Position position, String color) {
        this.position = position;
        this.color = color;
    }

    public Element(Position position, Position secondPosition, String color) {
        this.position = position;
        this.secondPosition = secondPosition;
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

    public Position getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(Position newPosition) {
        this.secondPosition = newPosition;
    }
}
