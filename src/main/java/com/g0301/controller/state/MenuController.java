package com.g0301.controller.state;

import com.g0301.gui.Gui;
import com.g0301.state.*;
import com.g0301.viewer.state.MenuViewer;

import java.io.IOException;

public class MenuController extends StateController implements KeyboardListener {
    private final MenuViewer menuViewer;

    public MenuController(GameState gameState, Gui gui) {
        super(gameState, gui);
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

    @Override
    public void step() throws IOException {
        gameState.getActiveButton().highlight("#FF0000");
        gameState.lowlightButtons();
        menuViewer.draw();
    }

    @Override
    public void getNextState() {
        if(gameState.getSelectedIndex() == 0)
            nextState = new GameModeState(gameState.getGame(), gui);
        else if (gameState.getSelectedIndex() == 1)
            nextState = new InstructionState(gameState.getGame(), gui);
        else if (gameState.getSelectedIndex() == 2)
            nextState = null;
    }
}
