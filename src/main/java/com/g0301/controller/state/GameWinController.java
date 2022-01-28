package com.g0301.controller.state;

import com.g0301.gui.Gui;
import com.g0301.state.*;
import com.g0301.viewer.state.GameWinViewer;

import java.io.IOException;

public class GameWinController extends StateController implements KeyboardListener {

    private GameWinViewer gameWinViewer;

    public GameWinController(GameState gameState, Gui gui, String winningPlayer) {
        super(gameState, gui);
        gameWinViewer = new GameWinViewer(gui, gameState.getButtons(), winningPlayer);
    }

    @Override
    public void step() throws IOException {
        gameState.getActiveButton().highlight("#FF0000");
        gameState.lowlightButtons();
        gameWinViewer.draw();
    }

    @Override
    public void getNextState() {
        if(gameState.getSelectedIndex() == 0 && gameState.get_classicGame()){
            nextState= new OnePlayerState(gameState.getGame(),gui);
        }
        else if (gameState.getSelectedIndex() == 0 && !gameState.get_survivalGame() && !gameState.get_classicGame()) {
            nextState = new TwoPlayerState(gameState.getGame(), gui);
        }
        else if ( gameState.getSelectedIndex() == 0 && gameState.get_survivalGame()){
            nextState = new SurvivalState(gameState.getGame(), gui);
        }
        else if (gameState.getSelectedIndex() == 1)
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
