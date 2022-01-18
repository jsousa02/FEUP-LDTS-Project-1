package com.g0301.model;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * @brief Updates the x coordinate value
     * @param x The new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @brief Updates the y coordinate value
     * @param y The new y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @brief Updates the position
     * @param position The new position
     */
    void setPosition(Position position) {
        this.setX(position.getX());
        this.setY(position.getY());
    }

    /**
     * @return The current position
     */
    public Position getPosition() {
        Position position=new Position(x,y);
        return position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * @brief Compares the position of two objects
     * @param o The object to be compared
     * @return True if the positions are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }

    /**
     * @return The position above the current one
     */
    public Position moveUp() {
        return new Position(this.x, this.y - 1);
    }

    /**
     * @return The position below the current one
     */
    public Position moveDown() {
        return new Position(this.x, this.y + 1);
    }

    /**
     * @return The position to the left of the current one
     */
    public Position moveLeft() {
        return new Position(this.x - 1, this.y);
    }

    /**
     * @return The position to the right of the current one
     */
    public Position moveRight() {
        return new Position(this.x + 1, this.y);
    }
}
