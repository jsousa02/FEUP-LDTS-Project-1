package com.g0301.viewer;

import com.g0301.gui.Gui;
import com.g0301.model.Car;

public class CarViewer implements ElementViewer<Car> {

    @Override
    public void drawElement(Car element, Gui gui) {
        gui.drawCar(element.getPosition(), element.getColor());
    }
}
