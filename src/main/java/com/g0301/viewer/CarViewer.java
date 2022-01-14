package com.g0301.viewer;

import com.g0301.Gui.Gui;
import com.g0301.model.Car;

public class CarViewer implements ElementViewer<Car> {

    /**
     * @breif Handles car drawing
     * @param element The car to be drawn
     * @param gui The GUI in which the car is drawn
     */
    @Override
    public void drawElement(Car element, Gui gui) {
        gui.drawCar(element.getPosition(), element.getColor());
    }
}
