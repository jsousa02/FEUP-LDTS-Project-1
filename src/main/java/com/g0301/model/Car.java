package com.g0301.model;

import com.g0301.gui.Gui;

import java.util.HashSet;
import java.util.Set;

public abstract class Car extends Element {
    private final Set<Trail> trailList = new HashSet<>();
    private Gui.ACTION previousMovement;
    private boolean isAlive = true;

    public Car(Position position, String color) {
        super(position, color);
        previousMovement = Gui.ACTION.RIGHT;
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
        if (trailList.isEmpty()){
            return false;
        }
        for (Trail trail: trailList) {
            if (getPosition().equals(trail.getPosition())) {
                isAlive = false;
                return true;
            }
        }
        return false;
    }

    public void setPreviousMovement(Gui.ACTION action){
        previousMovement = action;
    }

    public Gui.ACTION getPreviousMovement() {
        return previousMovement;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setDead() {
        isAlive = false;
    }
    public void setAlive(){
        isAlive= true;
    }
}
