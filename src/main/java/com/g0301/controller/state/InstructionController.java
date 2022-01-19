package com.g0301.controller.state;

import com.g0301.gui.Gui;
import com.g0301.state.GameState;
import com.g0301.state.KeyboardListener;
import com.g0301.state.MenuState;
import com.g0301.viewer.state.InstructionViewer;

import java.io.IOException;

public class InstructionController extends StateController implements KeyboardListener {
    private InstructionViewer instructionViewer;

    public InstructionController(GameState gameState, Gui gui) {
        super(gameState, gui);
        this.instructionViewer = new InstructionViewer(gui, gameState.getButtons());
    }


    @Override
    public void keyPressed(Gui.ACTION action) {
        if(action == Gui.ACTION.ENTER) {
            getNextState();
            gameState.changeState(nextState);
        }
    }

    @Override
    public void step() throws IOException {
        gameState.getActiveButton().highlight("#FF0000");
        instructionViewer.draw();
    }

    @Override
    public void getNextState() {
        nextState = new MenuState(gameState.getGame(), gui);
    }
}
