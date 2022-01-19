package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.ArenaController;
import com.g0301.gui.Gui;
import com.g0301.model.Arena;

import java.io.IOException;
import java.util.Arrays;

public class PlayingState extends GameState {
    private ArenaController arenaController;

    public PlayingState(Game game, Gui gui) {
        super(game, Arrays.asList());
        arenaController = new ArenaController(gui, new Arena(game.getWidth(), game.getHeight() - 2));
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
