package com.g0301.state;

import com.g0301.Game;
import com.g0301.model.Button;

import java.io.IOException;
import java.util.List;

public abstract class GameState {
    protected final Game game;
    private boolean classicGame;
    private boolean survivalGame;
    protected List<Button> buttons;
    private int selectedIndex = 0;

    public GameState(Game game, List<Button> buttons, boolean classicGame, boolean survivalGame) {
        this.game = game;
        this.buttons = buttons;
        this.classicGame=classicGame;
        this.survivalGame=survivalGame;
    }

    public abstract void start();

    public abstract void step() throws IOException;

    public void changeState(GameState gameState) {
        game.setGameState(gameState);
    }
    public boolean get_classicGame(){
        return classicGame;
    }
    public boolean get_survivalGame(){
        return survivalGame;
    }

    public Game getGame() {
        return game;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public int getSelectedIndex(){
        return selectedIndex;
    }

    public void nextButton() {
        if (selectedIndex == (buttons.size() - 1)) {
            selectedIndex = 0;
        }
        else selectedIndex++;
    }

    public void previousButton()  {
        if (selectedIndex == 0) {
            selectedIndex = (buttons.size()-1);
        }
        else selectedIndex--;
    }

    public void lowlightButtons() {
        for (Button button : buttons) {
            if(button != getActiveButton())
                button.lowlight("#000000");
        }
    }

    public Button getActiveButton() {
        return buttons.get(getSelectedIndex());
    }
}
