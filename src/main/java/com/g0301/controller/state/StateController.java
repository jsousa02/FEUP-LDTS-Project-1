package com.g0301.controller.state;

import com.g0301.gui.Gui;
import com.g0301.state.GameState;

import java.io.IOException;

public abstract class StateController {
    protected GameState gameState;
    protected GameState nextState;
    protected Gui gui;

    public StateController(GameState gameState, Gui gui) {
        this.gameState = gameState;
        this.gui = gui;
    }

    public abstract void step() throws IOException;

    public abstract void getNextState();
}
