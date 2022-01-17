package com.g0301.viewer;

import com.g0301.Gui.Gui;
import com.g0301.model.Element;

public class PortalViewer implements ElementViewer {
    @Override
    public void drawElement(Element element, Gui gui) {
        gui.drawPortal(element.getPosition(), element.getSecondPosition(), element.getColor());
    }
}
