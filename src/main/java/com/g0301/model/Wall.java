package com.g0301.model;

import java.util.Objects;

public class Wall extends Element {
    public Wall(Position position, String color) {
        super(position, color);
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        Wall w = (Wall) obj;
        return w.getPosition().equals(((Wall) obj).getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
