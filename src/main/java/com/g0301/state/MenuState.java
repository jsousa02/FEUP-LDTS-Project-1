package com.g0301.state;
import com.g0301.Game;
import com.g0301.gui.Gui;
import com.g0301.controller.MenuController;
import com.g0301.model.Button;
import com.g0301.model.Position;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MenuState extends GameState {
    private MenuController menuController;
    private int SelectedIndex = 0;

    public MenuState(Game game, Gui gui){
        super(game, Arrays.asList(new Button(new Position(25, 10), "#FFFF00", "#000000", "Play!", 15, 3),
                new Button(new Position(25, 20), "#FFFF00", "#000000", "Instructions", 15, 3),
                new Button(new Position(25, 30), "#FFFF00", "#000000", "Exit", 15, 3)));
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

    public List<Button> getButtons(){
        return buttons;
    }

    public int getSelectedIndex(){
        return SelectedIndex;
    }

    public void addButton(Button button) {
        getButtons().add(button);
    }

    public void nextButton() {
        if (SelectedIndex == (buttons.size() - 1)) {
            SelectedIndex = 0;
        }
        else SelectedIndex++;
    }

    public void previousButton()  {
        if (SelectedIndex == 0) {
            SelectedIndex = (buttons.size()-1);
        }
        else SelectedIndex--;
    }
}
