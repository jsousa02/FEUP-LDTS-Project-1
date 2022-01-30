package com.g0301.state;

import com.g0301.Game;
import com.g0301.gui.Gui;
import com.g0301.controller.state.MenuController;
import com.g0301.model.Button;
import com.g0301.model.Position;

import java.io.IOException;
import java.util.Arrays;

public class MenuState extends GameState {
    private MenuController menuController;

    public MenuState(Game game, Gui gui){
        super(game, Arrays.asList(new Button(new Position(gui.getWidth() / 2, 20), "#000000", "#FFFFFF", "Play", 15, 3),
                new Button(new Position(gui.getWidth() / 2, 30), "#000000", "#FFFFFF", "Instructions", 15, 3),
                new Button(new Position(gui.getWidth() / 2, 40), "#000000", "#FFFFFF", "Exit", 15, 3)),false,false);
        this.menuController = new MenuController(this, gui);
    }

    @Override
    public void start() {
        game.getKeyboardObserver().setListener(menuController);
    }

    @Override
    public void step() throws IOException {
        menuController.step();
    }
}
