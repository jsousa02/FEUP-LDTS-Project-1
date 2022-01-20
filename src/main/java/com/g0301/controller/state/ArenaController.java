package com.g0301.controller.state;

import com.g0301.controller.CarController;
import com.g0301.gui.Gui;
import com.g0301.model.*;
import com.g0301.state.GameOverState;
import com.g0301.state.GameState;
import com.g0301.state.GameWinState;
import com.g0301.state.KeyboardListener;
import com.g0301.viewer.ArenaViewer;

import java.io.IOException;
import java.util.Arrays;

public class ArenaController extends StateController implements KeyboardListener {

    private final CarController carController;
    private final ArenaViewer arenaViewer;
    private final Gui gui;
    private Gui.ACTION movement = Gui.ACTION.RIGHT;
    private final CarController botController;
    private Arena arena;

    public ArenaController(GameState gameState, Gui gui) {
        super(gameState, gui);
        this.arena = new Arena(90, 60);
        this.carController = new CarController(arena.getCar());
        this.gui = gui;
        this.arenaViewer = new ArenaViewer(gui, Arrays.asList(), arena);
        this.botController = new CarController(arena.getBot());
    }


    public void step() throws IOException {
        getNextState();
        arenaViewer.draw();
        action(movement);
    }

    @Override
    public void getNextState() {
        if(!carController.getCar().isAlive()) {
            nextState = new GameOverState(gameState.getGame(), gui);
            gameState.changeState(nextState);
        }
        else if (!botController.getCar().isAlive()) {
            nextState = new GameWinState(gameState.getGame(), gui);
            gameState.changeState(nextState);
        }
    }

    public void action(Gui.ACTION action) {
        Position currentPosition = carController.getCar().getPosition();
        Position nextPosition = carController.makeMovement(movement);
        Position botCurrentPosition = botController.getCar().getPosition();
        Position botNextPosition = botController.botMovement();
        /**
        if (!botController.getCar().collisionWithOwnTrail() && !arena.wallCollision() && !arena.botCollisionWithCarTrail()) {
            botController.getCar().getTrailList().add(new Trail(botCurrentPosition, "#FFFF00"));
            if(!arena.enterPortalThroughExit(action) && !arena.enterPortalThroughStart(action)) {}
                //botController.moveCar(botNextPosition);
        }*/
        if (!carController.getCar().collisionWithOwnTrail() && !arena.wallCollision() && !arena.carCollisionWithBotTrail()) {
            carController.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            if(!arena.enterPortalThroughExit(action) && !arena.enterPortalThroughStart(action))
                carController.moveCar(nextPosition);
        }
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        movement = action;
    }

    public CarController getCarController() {
        return carController;
    }
    public CarController getBotController(){
        return botController;
    }
}
