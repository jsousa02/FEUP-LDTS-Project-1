package com.g0301.model;

import java.util.ArrayList;
import java.util.List;

public class Car extends Element {

    private List<Trail> trailList = new ArrayList<>();

    public Car(Position position, String color) {
        super(position, color);
    }

    public List<Trail> getTrailList() {
        return trailList;
    }
}
