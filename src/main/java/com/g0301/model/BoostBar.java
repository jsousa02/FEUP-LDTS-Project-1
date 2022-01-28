package com.g0301.model;

import java.util.ArrayList;
import java.util.List;

public class BoostBar extends Element {

    List<Trail> boostBar = new ArrayList<>();
    private boolean isActive = false;
    public static int holdTime = 0;
    public static int releaseTime = 0;

    public BoostBar(Position position, String color) {
        super(position, color);
    }

    public void createBoostBar() {
        for(int i = 0; i < 10; i++) {
            boostBar.add(new Trail(new Position(position.getX() + i, position.getY()), color));
        }
    }

    public void increase() {
        if(boostBar.size() < 10) {
            boostBar.add(new Trail(new Position(position.getX() + boostBar.size(), position.getY()), color));
        }
    }

    public void decrease() {
        if (boostBar.size() > 0) {
            boostBar.remove(boostBar.get(boostBar.size() - 1));
            holdTime = 0;
            releaseTime = 0;
        }
    }

    public List<Trail> getBoostBarTrails() {
        return boostBar;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(int time) {
        holdTime = time;
    }

    public int getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(int time) {
        releaseTime = time;
    }

    public void activate() {
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }

    public boolean isEmpty() {
        return boostBar.size() == 0;
    }

    public void reverseDecrease() {
        if(boostBar.size() > 0) {
            boostBar.remove(0);
            holdTime = 0;
            releaseTime = 0;
        }
    }
}
