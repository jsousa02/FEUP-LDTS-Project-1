package com.g0301.model;

public class Wall extends Element {
    public Wall(Position position, String color) {
        super(position, color);
    }

    /**
     * @return The wall's color
     */
    public String getColor() {
        return color;
    }
}
