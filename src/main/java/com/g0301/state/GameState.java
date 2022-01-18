package com.g0301.state;

import com.g0301.Game;
import com.g0301.model.Button;

import java.io.IOException;
import java.util.List;

public abstract class GameState {
    protected final Game game;
    protected List<Button> buttons;

    public GameState(Game game, List<Button> buttons) {
        this.game = game;
        this.buttons = buttons;
    }

    public abstract void start();

    public abstract void step() throws IOException;

    public void changeState(GameState gameState) {
        game.setGameState(gameState);
    }

    public Game getGame() {
        return game;
    }

    public List<Button> getButtons() {
        return buttons;
    }
}
