package com.g0301.controller.state;

import com.g0301.gui.Gui;
import com.g0301.state.GameState;
import com.g0301.state.KeyboardListener;
import com.g0301.state.MenuState;
import com.g0301.viewer.state.PauseViewer;

import java.io.IOException;

public class PauseController extends StateController implements KeyboardListener {

    private PauseViewer pauseViewer;

    public PauseController(GameState gameState, Gui gui) {
        super(gameState, gui);
        pauseViewer = new PauseViewer(gui, gameState.getButtons());
    }

    @Override
    public void step() throws IOException {
        gameState.getActiveButton().highlight("#FF0000");
        gameState.lowlightButtons();
        pauseViewer.draw();
    }

    @Override
    public void getNextState() {
        nextState = new MenuState(gameState.getGame(), gui);
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        getNextState();
        if(action == Gui.ACTION.ENTER)
            gameState.changeState(nextState);
    }
}
