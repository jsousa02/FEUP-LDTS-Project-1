package com.g0301.Gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.g0301.model.Position;

import java.io.IOException;

public interface Gui {

    enum ACTION {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        QUIT,
    }

    TextGraphics createTextGraphics();

    int getWidth();

    int getHeight();

    void drawBackground(TextGraphics textGraphics, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    boolean isActive();

    void drawCar(Position position, String color);

    void drawElement(TextGraphics graphics, Position position, String color, String text);

    void drawWall(Position position, String color);

    void drawTrail(Position position, String color);

    void drawPortal(Position startPosition, Position endPosition, String color);
}
