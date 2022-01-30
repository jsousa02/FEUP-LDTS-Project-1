package com.g0301;

import com.g0301.gui.*;
import com.g0301.state.GameState;
import com.g0301.state.MenuState;

import java.awt.*;
import java.io.IOException;

public class Game {
    private final int width;
    private final int height;
    private final int fps;
    private final Gui gui;
    private final KeyboardObserver keyboardObserver;
    private GameState gameState;

    private static Game singleton = null;

    private Game(int width, int height, int fps) throws IOException, FontFormatException {
        this.width = width;
        this.height = height;
        this.fps = fps;
        this.gui = new LanternaGUI(width, height);
        this.keyboardObserver = new KeyboardObserver();
        this.gameState = new MenuState(this, gui);
    }

    public static Game getInstance() throws IOException, FontFormatException {
        if(singleton == null)
            singleton = new Game(90, 60, 20);
        return singleton;
    }

    public void run() throws IOException {
        int frameTime = 1000 / fps;

        gui.addKeyboardListener(keyboardObserver);
        gameState.start();

        while(gui.isActive() && gameState != null) {
            long startTime = System.currentTimeMillis();

            gameState.step();

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {}
            }
        }
        gui.close();
    }

    public KeyboardObserver getKeyboardObserver() {
        return keyboardObserver;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        if(gameState != null)
            this.gameState.start();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
