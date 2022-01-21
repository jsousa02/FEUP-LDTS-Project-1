package com.g0301.controller.state;

import com.g0301.controller.CarController;
import com.g0301.controller.Player1Controller;
import com.g0301.controller.Player2Controller;
import com.g0301.gui.Gui;
import com.g0301.model.Position;
import com.g0301.model.Trail;
import com.g0301.model.TwoPlayerArena;
import com.g0301.state.GameState;
import com.g0301.state.GameWinState;
import com.g0301.state.KeyboardListener;
import com.g0301.viewer.state.TwoPlayerViewer;

import java.io.IOException;

public class TwoPlayerController extends StateController implements KeyboardListener {

    private final TwoPlayerArena twoPlayerArena;
    private final Player1Controller player1;
    private final Player2Controller player2;
    private final TwoPlayerViewer twoPlayerViewer;
    private Gui.ACTION player1movement = Gui.ACTION.RIGHT;
    private Gui.ACTION player2movement = Gui.ACTION.P2LEFT;

    public TwoPlayerController(GameState gameState, Gui gui) {
        super(gameState, gui);
        twoPlayerArena = new TwoPlayerArena(gameState.getGame().getWidth(), gameState.getGame().getHeight());
        player1 = new Player1Controller(twoPlayerArena.getPlayer1());
        player2 = new Player2Controller(twoPlayerArena.getPlayer2());
        twoPlayerViewer = new TwoPlayerViewer(gui, gameState.getButtons(), twoPlayerArena);
    }


    @Override
    public void step() throws IOException {
        getNextState();
        twoPlayerViewer.draw();
        movePlayer1(player1movement);
        movePlayer2(player2movement);
    }

    @Override
    public void getNextState() {
        if(!player1.getCar().isAlive()) {
            boolean classicGame=false;
            nextState = new GameWinState(gameState.getGame(), gui,classicGame);
            gameState.changeState(nextState);
        }
        else if (!player2.getCar().isAlive()) {
            boolean classicGame=false;
            nextState = new GameWinState(gameState.getGame(), gui,classicGame);
            gameState.changeState(nextState);
        }
    }

    public void movePlayer1(Gui.ACTION action) {
        Position currentPosition = player1.getCar().getPosition();
        Position nextPosition = player1.makeMovement(action);

        if(!player1.getCar().collisionWithOwnTrail() && !twoPlayerArena.wallCollision() && !twoPlayerArena.player1CollidesWithPlayer2()) {
            player1.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            if(!twoPlayerArena.enterPortalThroughStart(action, player1) && !twoPlayerArena.enterPortalThroughExit(action, player1)) {
                player1.moveCar(nextPosition);
            }
        }
    }

    public void movePlayer2(Gui.ACTION action) {
        Position currentPosition = player2.getCar().getPosition();
        Position nextPosition = player2.makeMovement(action);

        if(!player2.getCar().collisionWithOwnTrail() && !twoPlayerArena.wallCollision() && !twoPlayerArena.player2CollidesWithPlayer1()) {
            player2.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            if(!twoPlayerArena.enterPortalThroughStart(action, player2) && !twoPlayerArena.enterPortalThroughExit(action, player2)) {
                player2.moveCar(nextPosition);
            }
        }
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        if(action == Gui.ACTION.DOWN || action == Gui.ACTION.UP || action == Gui.ACTION.LEFT || action == Gui.ACTION.RIGHT)
            player1movement = action;
        else if (action == Gui.ACTION.P2UP || action == Gui.ACTION.P2DOWN || action == Gui.ACTION.P2LEFT || action == Gui.ACTION.P2RIGHT)
            player2movement = action;
    }
}
