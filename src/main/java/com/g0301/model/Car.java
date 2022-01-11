package com.g0301.model;

import java.util.HashSet;
import java.util.Set;

public class Car extends Element {
    private final Set<Trail> trailList = new HashSet<>();

    public Car(Position position, String color) {
        super(position, color);
    }

    /**
     * @return The car's trail list
     */
    public Set<Trail> getTrailList() {
        return trailList;
    }

    /**
     * @brief Checks if the car collides with its own trail
     * @return True if there is a collision, false otherwise
     */
    public boolean collisionWithOwnTrail() {
        for (Trail trail: trailList) {
            if (getPosition().equals(trail.getPosition()))
                return true;
        }
        return false;
    }
}
