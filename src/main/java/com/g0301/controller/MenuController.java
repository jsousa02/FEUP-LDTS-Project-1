package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.state.GameState;
import com.g0301.state.InstructionState;
import com.g0301.state.KeyboardListener;
import com.g0301.state.PlayingState;
import com.g0301.viewer.menu.MenuViewer;

import java.io.IOException;

public class MenuController implements KeyboardListener {
    private final MenuViewer menuViewer;
    private final GameState gameState;
    private GameState nextState;
    private final Gui gui;

    public MenuController(GameState gameState, Gui gui) {
        this.gameState = gameState;
        this.gui = gui;
        this.menuViewer = new MenuViewer(gui, gameState.getButtons());
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        getNextState();
        if(action == Gui.ACTION.QUIT)
            gameState.changeState(null);
        if(action == Gui.ACTION.ENTER)
            gameState.changeState(nextState);
        if(action == Gui.ACTION.UP)
            gameState.previousButton();
        if(action == Gui.ACTION.DOWN)
            gameState.nextButton();
    }

    public void step() throws IOException {
        gameState.getActiveButton().highlight("#FF0000");
        gameState.lowlightButtons();
        menuViewer.draw();
    }

    public void getNextState() {
        if(gameState.getSelectedIndex() == 0)
            nextState = new PlayingState(gameState.getGame(), gui);
        else if (gameState.getSelectedIndex() == 1)
            nextState = new InstructionState(gameState.getGame(), gui);
        else if (gameState.getSelectedIndex() == 2)
            nextState = null;
    }
}
