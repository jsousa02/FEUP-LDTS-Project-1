package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.state.GameWinController;
import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.model.Position;

import java.io.IOException;
import java.util.Arrays;

public class GameWinState extends GameState {

    private GameWinController gameWinController;
    public GameWinState(Game game, Gui gui,boolean classicGame, boolean survivalGame, String winningPlayer) {
        super(game, Arrays.asList(new Button(new Position(gui.getWidth() / 2, 30), "#000000", "#FFFFFF", "Try again", 15, 3),
                new Button(new Position(gui.getWidth() / 2, 40), "#000000", "#FFFFFF", "Back", 15, 3)), classicGame,survivalGame);
        gameWinController = new GameWinController(this, gui, winningPlayer);
    }
    @Override
    public void start() {
        getGame().getKeyboardObserver().setListener(gameWinController);
    }

    @Override
    public void step() throws IOException {
        gameWinController.step();
    }
}
