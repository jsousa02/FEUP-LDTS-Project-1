package com.g0301.controller;

import com.g0301.gui.Gui;
import com.g0301.state.GameState;
import com.g0301.state.KeyboardListener;
import com.g0301.state.MenuState;
import com.g0301.viewer.menu.InstructionViewer;

import java.io.IOException;

public class InstructionController implements KeyboardListener {
    private InstructionViewer instructionViewer;
    private final GameState gameState;
    private final Gui gui;

    public InstructionController(GameState gameState, Gui gui) {
        this.gameState = gameState;
        this.gui = gui;
        this.instructionViewer = new InstructionViewer(gui, gameState.getButtons());
    }


    @Override
    public void keyPressed(Gui.ACTION action) {
        if(action == Gui.ACTION.ENTER)
            gameState.changeState(new MenuState(gameState.getGame(), gui));
    }

    public void step() throws IOException {
        gameState.getActiveButton().highlight("#FF0000");
        instructionViewer.draw();
    }
}
