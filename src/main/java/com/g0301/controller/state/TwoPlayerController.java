package com.g0301.controller.state;

import com.g0301.controller.Player1Controller;
import com.g0301.controller.Player2Controller;
import com.g0301.gui.Gui;
import com.g0301.model.Position;
import com.g0301.model.Trail;
import com.g0301.model.TwoPlayerArena;
import com.g0301.state.GameState;
import com.g0301.state.GameWinState;
import com.g0301.state.KeyboardListener;
import com.g0301.state.PauseState;
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
        boolean classicGame=false;
        boolean survivalGame=false;
        if(!player1.getCar().isAlive()) {
            nextState = new GameWinState(gameState.getGame(), gui,classicGame,survivalGame, "Player 2 wins!");
            gameState.changeState(nextState);
        }
        else if (!player2.getCar().isAlive()) {
            nextState = new GameWinState(gameState.getGame(), gui,classicGame,survivalGame, "Player 1 wins!");
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

                if(twoPlayerArena.getPlayer1BoostBar().isActive()) {
                    player1.getCar().changeSpeed(2);
                    player1.moveCar(nextPosition);
                }
                else {
                    player1.getCar().changeSpeed(1);
                    player1.moveCar(nextPosition);
                }
            }
        }
    }

    public void movePlayer2(Gui.ACTION action) {
        Position currentPosition = player2.getCar().getPosition();
        Position nextPosition = player2.makeMovement(action);

        if(!player2.getCar().collisionWithOwnTrail() && !twoPlayerArena.wallCollision() && !twoPlayerArena.player2CollidesWithPlayer1()) {
            player2.getCar().getTrailList().add(new Trail(currentPosition, "#FF0000"));
            if(!twoPlayerArena.enterPortalThroughStart(action, player2) && !twoPlayerArena.enterPortalThroughExit(action, player2)) {
                player2.moveCar(nextPosition);

                if(twoPlayerArena.getPlayer2BoostBar().isActive()) {
                    player2.getCar().changeSpeed(2);
                    player2.moveCar(nextPosition);
                }
                else {
                    player2.getCar().changeSpeed(1);
                    player2.moveCar(nextPosition);
                }
            }
        }
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        if(action == Gui.ACTION.P1BOOST) {
            twoPlayerArena.getPlayer1BoostBar().setHoldTime(twoPlayerArena.getPlayer1BoostBar().getHoldTime() + 1);
            if(twoPlayerArena.getPlayer1BoostBar().isEmpty())
                twoPlayerArena.getPlayer1BoostBar().deactivate();
            else
                twoPlayerArena.getPlayer1BoostBar().activate();

            if(twoPlayerArena.getPlayer1BoostBar().getHoldTime() > 10) {
                twoPlayerArena.getPlayer1BoostBar().decrease();
            }
        }
        else if(action == Gui.ACTION.P2BOOST) {
            twoPlayerArena.getPlayer2BoostBar().setHoldTime(twoPlayerArena.getPlayer2BoostBar().getHoldTime() + 1);
            if(twoPlayerArena.getPlayer2BoostBar().isEmpty())
                twoPlayerArena.getPlayer2BoostBar().deactivate();
            else
                twoPlayerArena.getPlayer2BoostBar().activate();

            if(twoPlayerArena.getPlayer2BoostBar().getHoldTime() > 10) {
                twoPlayerArena.getPlayer2BoostBar().reverseDecrease();
            }
        }
        else if(action == Gui.ACTION.DOWN || action == Gui.ACTION.UP || action == Gui.ACTION.LEFT || action == Gui.ACTION.RIGHT) {
            twoPlayerArena.getPlayer1BoostBar().setReleaseTime(twoPlayerArena.getPlayer1BoostBar().getReleaseTime() + 1);
            twoPlayerArena.getPlayer1BoostBar().deactivate();

            if(twoPlayerArena.getPlayer1BoostBar().getReleaseTime() > 2) {
                twoPlayerArena.getPlayer1BoostBar().increase();
            }

            player1movement = action;
        }
        else if (action == Gui.ACTION.P2UP || action == Gui.ACTION.P2DOWN || action == Gui.ACTION.P2LEFT || action == Gui.ACTION.P2RIGHT) {
            twoPlayerArena.getPlayer2BoostBar().setReleaseTime(twoPlayerArena.getPlayer2BoostBar().getReleaseTime() + 1);
            twoPlayerArena.getPlayer2BoostBar().deactivate();

            if(twoPlayerArena.getPlayer2BoostBar().getReleaseTime() > 2) {
                twoPlayerArena.getPlayer2BoostBar().increase();
            }
            player2movement = action;
        }
        else if(action == Gui.ACTION.PAUSE) {
            gameState.changeState(new PauseState(gameState.getGame(), gui));
        }
    }
}
