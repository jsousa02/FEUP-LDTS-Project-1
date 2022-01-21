package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.state.OnePlayerController;
import com.g0301.gui.Gui;

import java.io.IOException;
import java.util.Arrays;

public class OnePlayerState extends GameState {

    private OnePlayerController onePlayerController;

    public OnePlayerState(Game game, Gui gui) {
        super(game, Arrays.asList(),false,false);
        onePlayerController = new OnePlayerController(this, gui);
    }

    @Override
    public void start() {
        game.getKeyboardObserver().setListener(onePlayerController);
    }

    @Override
    public void step() throws IOException {
        onePlayerController.step();
    }
}
