package com.g0301.controller.state;

import com.g0301.controller.BotController;
import com.g0301.controller.Player1Controller;
import com.g0301.gui.Gui;
import com.g0301.model.OnePlayerArena;
import com.g0301.model.Position;
import com.g0301.model.Trail;
import com.g0301.state.GameOverState;
import com.g0301.state.GameState;
import com.g0301.state.GameWinState;
import com.g0301.state.KeyboardListener;
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
            nextState = new GameWinState(gameState.getGame(), gui, classicGame,survivalGame);
            gameState.changeState(nextState);
        }
    }

    public void movePlayer1(Gui.ACTION action) {
        Position currentPosition = player1.getCar().getPosition();
        Position nextPosition = player1.makeMovement(action);

        if(!player1.getCar().collisionWithOwnTrail() && !onePlayerArena.wallCollision() && !onePlayerArena.playerCollisionWithBotTrail()) {
            player1.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            if(!onePlayerArena.enterPortalThroughStart(action, player1) && !onePlayerArena.enterPortalThroughExit(action, player1)) {
                player1.moveCar(nextPosition);
            }
        }
    }

    public void action() {
        Position botCurrentPosition = bot.getCar().getPosition();
        List<Integer> possibleMoves= new ArrayList<>();
        Position botNextPosition;

        if(onePlayerArena.upClearPosition(botCurrentPosition)&& onePlayerArena.getBot().getPreviousMovement()!=Gui.ACTION.DOWN){
            possibleMoves.add(1);
        }
        if (onePlayerArena.downClearPosition(botCurrentPosition)&& onePlayerArena.getBot().getPreviousMovement()!=Gui.ACTION.UP){
            possibleMoves.add(2);
        }
        if (onePlayerArena.leftClearPosition(botCurrentPosition)&& onePlayerArena.getBot().getPreviousMovement()!=Gui.ACTION.RIGHT){
            possibleMoves.add(3);
        }
        if (onePlayerArena.rightClearPosition(botCurrentPosition)&& onePlayerArena.getBot().getPreviousMovement()!=Gui.ACTION.LEFT){
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
            bot.getCar().getTrailList().add(new Trail(botCurrentPosition, "#FFFF00"));
            if(!onePlayerArena.enterPortalThroughStart(onePlayerArena.getBot().getPreviousMovement(), bot) && !onePlayerArena.enterPortalThroughExit(onePlayerArena.getBot().getPreviousMovement(), bot)){
                bot.moveCar(botNextPosition);
            }
        }
    }

    public Position botMove(int move, Position initialPosition) {
        Position finalPosition;
        if ( move ==1){
            finalPosition= initialPosition.moveUp();
            onePlayerArena.getBot().setPreviousMovement(Gui.ACTION.UP);
        }
        else if (move==2){
            finalPosition= initialPosition.moveDown();
            onePlayerArena.getBot().setPreviousMovement(Gui.ACTION.DOWN);
        }
        else if (move==3){
            finalPosition= initialPosition.moveLeft();
            onePlayerArena.getBot().setPreviousMovement(Gui.ACTION.LEFT);
        }
        else if (move==4){
            finalPosition=initialPosition.moveRight();
            onePlayerArena.getBot().setPreviousMovement(Gui.ACTION.RIGHT);
        }
        else finalPosition=initialPosition.moveUp();
        return finalPosition;
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        if(action == Gui.ACTION.UP || action == Gui.ACTION.RIGHT || action == Gui.ACTION.LEFT || action == Gui.ACTION.DOWN)
            player1Movement = action;
    }
}
