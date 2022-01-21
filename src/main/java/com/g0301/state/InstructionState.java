package com.g0301.state;

import com.g0301.Game;
import com.g0301.controller.state.InstructionController;
import com.g0301.gui.Gui;
import com.g0301.model.Button;
import com.g0301.model.Position;

import java.io.IOException;
import java.util.Arrays;

public class InstructionState extends GameState {
    private InstructionController instructionController;

    public InstructionState(Game game, Gui gui) {
        super(game, Arrays.asList(new Button(new Position(gui.getWidth() / 2, 50), "#000000", "#FFFFFF", "Back", 15, 3)),false,false);
        instructionController = new InstructionController(this, gui);
    }

    @Override
    public void start() {
        game.getKeyboardObserver().setListener(instructionController);
    }

    @Override
    public void step() throws IOException {
        instructionController.step();
    }
}
