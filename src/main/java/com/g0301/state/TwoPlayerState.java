package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.state.TwoPlayerController;
import com.g0301.gui.Gui;

import java.io.IOException;
import java.util.Arrays;

public class TwoPlayerState extends GameState {

    private TwoPlayerController twoPlayerController;

    public TwoPlayerState(Game game, Gui gui) {
        super(game, Arrays.asList(),false,false);
        twoPlayerController = new TwoPlayerController(this, gui);
    }

    @Override
    public void start() {
        game.getKeyboardObserver().setListener(twoPlayerController);
    }

    @Override
    public void step() throws IOException {
        twoPlayerController.step();
    }
}
