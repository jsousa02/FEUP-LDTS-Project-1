package com.g0301.viewer;

import com.g0301.gui.Gui;;
import com.g0301.model.Portal;

public class PortalViewer implements ElementViewer<Portal> {

    @Override
    public void drawElement(Portal element, Gui gui) {
        gui.drawPortal(element.getPosition(), element.getSecondPosition(), element.getColor());
    }
}
