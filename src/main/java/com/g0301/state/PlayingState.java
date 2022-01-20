package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.state.ArenaController;
import com.g0301.gui.Gui;

import java.io.IOException;
import java.util.Arrays;

public class PlayingState extends GameState {
    private ArenaController arenaController;

    public PlayingState(Game game, Gui gui) {
        super(game, Arrays.asList());
        arenaController = new ArenaController(this, gui);
    }

    @Override
    public void start() {
        game.getKeyboardObserver().setListener(arenaController);
    }

    @Override
    public void step() throws IOException {
        arenaController.step();
    }
}
