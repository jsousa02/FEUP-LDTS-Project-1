package com.g0301.viewer;

import com.g0301.gui.Gui;
import com.g0301.model.Element;

public interface ElementViewer<T extends Element> {
    void drawElement(T element, Gui gui);
}
