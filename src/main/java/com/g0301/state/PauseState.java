package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.state.PauseController;
import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.model.Position;

import java.io.IOException;
import java.util.Arrays;

public class PauseState extends GameState {

    private PauseController pauseController;

    public PauseState(Game game, Gui gui) {
        super(game, Arrays.asList(new Button(new Position(gui.getWidth() / 2, 30), "#000000", "#FFFFFF", "Exit", 15, 3)), true ,true);
        pauseController = new PauseController(this, gui);
    }

    @Override
    public void start() {
        game.getKeyboardObserver().setListener(pauseController);
    }

    @Override
    public void step() throws IOException {
        pauseController.step();
    }
}
