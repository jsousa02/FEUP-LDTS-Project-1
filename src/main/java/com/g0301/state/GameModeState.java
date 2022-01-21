package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.state.GameModeController;
import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.model.Position;

import java.io.IOException;
import java.util.Arrays;

public class GameModeState extends GameState {
    private GameModeController gameModeController;

    public GameModeState(Game game , Gui gui) {
        super(game, Arrays.asList(new Button(new Position(gui.getWidth() / 2, 20), "#000000", "#FFFFFF", "Single Player Classic", 15, 3),
            new Button(new Position(gui.getWidth() / 2, 30), "#000000", "#FFFFFF", "1v1 2 players", 15, 3),
            new Button(new Position(gui.getWidth() / 2, 40), "#000000", "#FFFFFF", "Survival", 15, 3),
            new Button(new Position(gui.getWidth() / 2, 50), "#000000", "#FFFFFF", "Back", 15, 3)
        ),false,false);
        this.gameModeController = new GameModeController(this, gui);
    }

    @Override
    public void start() {
        game.getKeyboardObserver().setListener(gameModeController);
    }

    @Override
    public void step() throws IOException {
        gameModeController.step();
    }
}
