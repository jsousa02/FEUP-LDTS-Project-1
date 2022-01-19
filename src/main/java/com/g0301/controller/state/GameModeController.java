package com.g0301.controller.state;

import com.g0301.gui.Gui;
import com.g0301.state.GameState;
import com.g0301.state.KeyboardListener;
import com.g0301.state.MenuState;
import com.g0301.state.PlayingState;
import com.g0301.viewer.state.GameModeViewer;

import java.io.IOException;


public class GameModeController extends StateController implements KeyboardListener {
    private final GameModeViewer gameModeViewer;

    public GameModeController(GameState gameState, Gui gui) {
        super(gameState, gui);
        this.gameModeViewer = new GameModeViewer(gui, gameState.getButtons());
    }

    @Override
    public void step() throws IOException {
        gameState.getActiveButton().highlight("#FF0000");
        gameState.lowlightButtons();
        gameModeViewer.draw();
    }

    @Override
    public void getNextState() {
        if(gameState.getSelectedIndex() == 0)
            nextState = new PlayingState(gameState.getGame(), gui);
        //TODO implement other game modes
        else if (gameState.getSelectedIndex() == 3)
            nextState = new MenuState(gameState.getGame(), gui);
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        getNextState();
        if(action == Gui.ACTION.UP)
            gameState.previousButton();
        if(action == Gui.ACTION.DOWN)
            gameState.nextButton();
        if(action == Gui.ACTION.ENTER)
            gameState.changeState(nextState);
    }
}
