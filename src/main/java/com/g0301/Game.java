package com.g0301;

import com.g0301.Gui.*;
import com.g0301.controller.ArenaController;
import com.g0301.model.Arena;
import com.g0301.viewer.ArenaViewer;

import java.awt.*;
import java.io.IOException;

public class Game {
    private final int width;
    private final int height;
    private final int fps;
    private final LanternaGUI gui;
    private final ArenaViewer arenaViewer;
    private final ArenaController arenaController;
    private final KeyboardObserver keyboardObserver;
    private Arena arena;

    private static Game singleton = null;

    private Game(int width, int height, int fps) throws IOException, FontFormatException {
        this.arena = new Arena(width, height);
        this.width = width;
        this.height = height;
        this.fps = fps;
        this.gui = new LanternaGUI(width, height);
        this.arenaViewer = new ArenaViewer(gui, arena);
        this.arenaController = new ArenaController(arena);
        this.keyboardObserver = new KeyboardObserver();
    }

    /**
     * @return The single game instance
     */
    public static Game getInstance() throws IOException, FontFormatException {
        if(singleton == null)
            singleton = new Game(60, 60, 30);
        return singleton;
    }

    /**
     * @brief Runs the game
     */
    public void run() throws IOException {
        int frameTime = 1000 / fps;

        keyboardObserver.setListener(arenaController);
        gui.addKeyboardListener(keyboardObserver);

        while(gui.isActive()) {
            long startTime = System.currentTimeMillis();

            Gui.ACTION nextMovement = arenaController.getCarController().getCar().getPreviousMovement();
            arenaController.step(nextMovement);
            arenaViewer.draw();
            //System.out.println("Draw");

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

    /**
     * @return The game's keyboard observer
     */
    public KeyboardObserver getKeyboardObserver() {
        return keyboardObserver;
    }
}
