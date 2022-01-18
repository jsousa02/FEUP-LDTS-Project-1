package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.state.GameState;
import com.g0301.state.KeyboardListener;
import com.g0301.state.PlayingState;
import com.g0301.viewer.menu.MenuViewer;

import java.io.IOException;

public class MenuController implements KeyboardListener {
    private final MenuViewer menuViewer;
    private final GameState gameState;
    private final Gui gui;

    public MenuController(GameState gameState, Gui gui) {
        this.gameState = gameState;
        this.gui = gui;
        this.menuViewer = new MenuViewer(gui, gameState.getButtons());
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        if(action == Gui.ACTION.QUIT)
            gameState.changeState(null);
        if(action == Gui.ACTION.ENTER)
            gameState.changeState(new PlayingState(gameState.getGame(), gui));
    }

    public void step() throws IOException {
        menuViewer.draw();
    }
}
