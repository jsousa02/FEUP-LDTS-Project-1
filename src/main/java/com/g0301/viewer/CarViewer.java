package com.g0301.viewer;

import com.g0301.Gui.Gui;
import com.g0301.model.Car;

public class CarViewer implements ElementViewer<Car> {

    @Override
    public void drawElement(Car element, Gui gui) {
        gui.drawCar(element.getPosition(), element.getColor());
        System.out.println(element.getPosition().getX());
        System.out.println(element.getPosition().getY());
    }
}
