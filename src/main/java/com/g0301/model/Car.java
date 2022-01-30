package com.g0301.model;

import com.g0301.gui.Gui;

import java.util.HashSet;
import java.util.Set;

public abstract class Car extends Element {
    private final Set<Trail> trailList = new HashSet<>();
    private Gui.ACTION previousMovement;
    private boolean isAlive = true;
    private int speed;

    public Car(Position position, String color) {
        super(position, color);
        previousMovement = Gui.ACTION.RIGHT;
        speed = 1;
    }

    public Set<Trail> getTrailList() {
        return trailList;
    }

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

    public void setAlive() {
        isAlive = true;
    }

    public int getSpeed() {
        return speed;
    }

    public void changeSpeed(int speed) {
        this.speed = speed;
    }
}
