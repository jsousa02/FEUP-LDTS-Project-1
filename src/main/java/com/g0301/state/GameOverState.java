package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.state.GameOverController;
import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.model.Position;

import java.io.IOException;
import java.util.Arrays;

public class GameOverState extends GameState {
    private GameOverController gameOverController;

    public GameOverState(Game game, Gui gui, boolean classicGame, boolean survivalGame) {
        super(game, Arrays.asList(new Button(new Position(gui.getWidth() / 2, 30), "#000000", "#FFFFFF", "Try again", 15, 3),
                new Button(new Position(gui.getWidth() / 2, 40), "#000000", "#FFFFFF", "Back", 15, 3)), classicGame,survivalGame);
        gameOverController = new GameOverController(this, gui);
    }

    @Override
    public void start() {
        getGame().getKeyboardObserver().setListener(gameOverController);
    }

    @Override
    public void step() throws IOException {
        gameOverController.step();
    }
}
