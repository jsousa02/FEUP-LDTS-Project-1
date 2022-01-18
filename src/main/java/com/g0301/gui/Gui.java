package com.g0301.gui;

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
        ENTER
    }

    TextGraphics createTextGraphics();

    int getWidth();

    int getHeight();

    void drawBackground(TextGraphics graphics, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    boolean isActive();

    void drawCar(Position position, String color);

    void drawElement(TextGraphics graphics, Position position, String color, String text);

    void drawWall(Position position, String color);

    void drawTrail(Position position, String color);

    void drawButton(Position buttonPosition, String bColor, String fColor, String text, int width, int height);

    void drawText(TextGraphics graphics, Position position, String color, String text);

    void drawTitle(Position position, String color, String textColor, String text);

    void drawPortal(Position startPosition, Position endPosition, String color);

}
