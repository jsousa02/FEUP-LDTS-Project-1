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
        ENTER,
        PAUSE,
        P2UP,
        P2DOWN,
        P2LEFT,
        P2RIGHT,
        P1BOOST,
        P2BOOST
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

    void drawLogo(String color, String textColor);

    void drawPortal(Position startPosition, Position endPosition, String color);

    void drawInstructions();

    void drawPlayer1BoostBar(Position position, String color);

    void drawPlayer2BoostBar(Position position, String color);

    void drawScore(String Score);

    void addKeyboardListener(KeyboardObserver observer);
}
