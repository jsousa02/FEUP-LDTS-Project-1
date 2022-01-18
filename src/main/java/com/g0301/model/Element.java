package com.g0301.model;

public abstract class Element {
    protected Position position;
    protected String foregroundColor;
    protected String color;
    protected String text;

    public Element(Position position, String color) {
        this.position = position;
        this.color = color;
    }

    /**
     * @return The current position
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * @brief Sets the position
     * @param newPosition The new position to be set
     */
    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    /**
     * @return The element's color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * @breif Sets a new color
     * @param newColor The new color to be set
     */
    public void setColor(String newColor) {
        this.color = newColor;
    }
}
