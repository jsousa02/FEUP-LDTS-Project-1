package com.g0301.controller.state;

import com.g0301.controller.BotController;
import com.g0301.controller.Player1Controller;
import com.g0301.gui.Gui;
import com.g0301.model.OnePlayerArena;
import com.g0301.model.Position;
import com.g0301.model.Trail;
import com.g0301.state.*;
import com.g0301.viewer.state.OnePlayerViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OnePlayerController extends StateController implements KeyboardListener {

    private final OnePlayerArena onePlayerArena;
    private final Player1Controller player1;
    private final BotController bot;
    private final OnePlayerViewer onePlayerViewer;
    private Gui.ACTION player1Movement = Gui.ACTION.RIGHT;

    public OnePlayerController(GameState gameState, Gui gui) {
        super(gameState, gui);
        onePlayerArena = new OnePlayerArena(gameState.getGame().getWidth(), gameState.getGame().getHeight());
        player1 = new Player1Controller(onePlayerArena.getPlayer1());
        onePlayerViewer = new OnePlayerViewer(gui, gameState.getButtons(), onePlayerArena);
        bot = new BotController(onePlayerArena.getBot());
    }

    @Override
    public void step() throws IOException {
        getNextState();
        onePlayerViewer.draw();
        action();
        movePlayer1(player1Movement);
    }

    @Override
    public void getNextState() {
        boolean classicGame=true;
        boolean survivalGame= false;
        if(!player1.getCar().isAlive()) {
            nextState = new GameOverState(gameState.getGame(), gui, classicGame, survivalGame);
            gameState.changeState(nextState);
        }
        else if (!bot.getCar().isAlive()) {
            nextState = new GameWinState(gameState.getGame(), gui, classicGame,survivalGame, "  You win!");
            gameState.changeState(nextState);
        }
    }

    public void movePlayer1(Gui.ACTION action) {
        Position currentPosition = player1.getCar().getPosition();
        Position nextPosition = player1.makeMovement(action);

        if(!player1.getCar().collisionWithOwnTrail() && !onePlayerArena.wallCollision() && !onePlayerArena.playerCollisionWithBotTrail() && !onePlayerArena.outOfBounds()) {
            player1.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            if(!onePlayerArena.enterPortalThroughStart(action, player1) && !onePlayerArena.enterPortalThroughExit(action, player1)) {
                player1.moveCar(nextPosition);

                if(onePlayerArena.getBoostBar().isActive()) {
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

    public void action() {
        Position botCurrentPosition = bot.getCar().getPosition();
        List<Integer> possibleMoves= new ArrayList<>();
        Position botNextPosition;

        if(onePlayerArena.upClearPosition(botCurrentPosition)&& onePlayerArena.getBot().getPreviousMovement()!=Gui.ACTION.DOWN){
            if(onePlayerArena.getBot().getPreviousMovement()==Gui.ACTION.UP){
                for(int i = 0; i<20;i++) {
                    possibleMoves.add(1);
                }
            }
            possibleMoves.add(1);
        }
        if (onePlayerArena.downClearPosition(botCurrentPosition)&& onePlayerArena.getBot().getPreviousMovement()!=Gui.ACTION.UP){
            if(onePlayerArena.getBot().getPreviousMovement()==Gui.ACTION.DOWN){
                for(int i = 0; i<20;i++) {
                    possibleMoves.add(2);
                }
            }
            possibleMoves.add(2);
        }
        if (onePlayerArena.leftClearPosition(botCurrentPosition)&& onePlayerArena.getBot().getPreviousMovement()!=Gui.ACTION.RIGHT){
            if(onePlayerArena.getBot().getPreviousMovement()==Gui.ACTION.LEFT){
                for(int i = 0; i<20;i++) {
                    possibleMoves.add(3);
                }
            }
            possibleMoves.add(3);
        }
        if (onePlayerArena.rightClearPosition(botCurrentPosition)&& onePlayerArena.getBot().getPreviousMovement()!=Gui.ACTION.LEFT){
            if(onePlayerArena.getBot().getPreviousMovement()==Gui.ACTION.RIGHT) {
                for (int i = 0; i < 20; i++) {
                    possibleMoves.add(4);
                }
            }
            possibleMoves.add(4);
        }
        if (possibleMoves.isEmpty()){
            botNextPosition= bot.botMovement();
        }
        else {
            int rand = new Random().nextInt(possibleMoves.size());
            int move = possibleMoves.get(rand);
            possibleMoves.clear();
            botNextPosition = botMove(move, botCurrentPosition);
        }
        if (!bot.getCar().collisionWithOwnTrail() && !onePlayerArena.wallCollision() && !onePlayerArena.botCollisionWithCarTrail()) {
            bot.getCar().getTrailList().add(new Trail(botCurrentPosition, "#FF0000"));
            if(!onePlayerArena.enterPortalThroughStart(onePlayerArena.getBot().getPreviousMovement(), bot) && !onePlayerArena.enterPortalThroughExit(onePlayerArena.getBot().getPreviousMovement(), bot)){
                bot.moveCar(botNextPosition);
            }
        }
    }

    public Position botMove(int move, Position initialPosition) {
        Position finalPosition;
        if ( move == 1){
            finalPosition= initialPosition.moveUp(1);
            onePlayerArena.getBot().setPreviousMovement(Gui.ACTION.UP);
        }
        else if (move == 2){
            finalPosition= initialPosition.moveDown(1);
            onePlayerArena.getBot().setPreviousMovement(Gui.ACTION.DOWN);
        }
        else if (move == 3){
            finalPosition= initialPosition.moveLeft(1);
            onePlayerArena.getBot().setPreviousMovement(Gui.ACTION.LEFT);
        }
        else if (move == 4){
            finalPosition=initialPosition.moveRight(1);
            onePlayerArena.getBot().setPreviousMovement(Gui.ACTION.RIGHT);
        }
        else finalPosition=initialPosition.moveUp(1);
        return finalPosition;
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        if(action == Gui.ACTION.P1BOOST) {
            onePlayerArena.getBoostBar().setHoldTime(onePlayerArena.getBoostBar().getHoldTime() + 1);
            if(onePlayerArena.getBoostBar().isEmpty())
                onePlayerArena.getBoostBar().deactivate();
            else
                onePlayerArena.getBoostBar().activate();

            if(onePlayerArena.getBoostBar().getHoldTime() > 10) {
                onePlayerArena.getBoostBar().decrease();
            }
        }
        else if(action == Gui.ACTION.PAUSE) {
            gameState.changeState(new PauseState(gameState.getGame(), gui));
        }
        else {
            onePlayerArena.getBoostBar().setReleaseTime(onePlayerArena.getBoostBar().getReleaseTime() + 1);
            onePlayerArena.getBoostBar().deactivate();

            if(onePlayerArena.getBoostBar().getReleaseTime() > 2) {
                onePlayerArena.getBoostBar().increase();
            }
            if(action == Gui.ACTION.UP || action == Gui.ACTION.DOWN || action == Gui.ACTION.LEFT || action == Gui.ACTION.RIGHT)
                player1Movement = action;
        }
    }
}
