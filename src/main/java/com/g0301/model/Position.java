package com.g0301.model;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(Position position) {
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public Position getPosition() {
        Position position=new Position(x,y);
        return position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }

    public Position moveUp(int speed) {
        return new Position(this.x, this.y - speed);
    }

    public Position moveDown(int speed) {
        return new Position(this.x, this.y + speed);
    }

    public Position moveLeft(int speed) {
        return new Position(this.x - speed, this.y);
    }

    public Position moveRight(int speed) {
        return new Position(this.x + speed, this.y);
    }
}
