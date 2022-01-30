package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.state.SurvivalController;
import com.g0301.gui.Gui;

import java.io.IOException;
import java.util.Arrays;

public class SurvivalState extends GameState {

    private SurvivalController survivalController;

    public SurvivalState(Game game, Gui gui) {
        super(game, Arrays.asList(),false,false);
        survivalController = new SurvivalController(this, gui);
    }

    @Override
    public void start() {
        game.getKeyboardObserver().setListener(survivalController);
    }

    @Override
    public void step() throws IOException {
        survivalController.step();
    }
}

