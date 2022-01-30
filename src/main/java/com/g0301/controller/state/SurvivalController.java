package com.g0301.controller.state;

import com.g0301.controller.BotController;
import com.g0301.controller.Player1Controller;
import com.g0301.gui.Gui;
import com.g0301.model.Position;
import com.g0301.model.SurvivalArena;
import com.g0301.model.Trail;
import com.g0301.state.*;
import com.g0301.viewer.state.SurvivalViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SurvivalController extends StateController implements KeyboardListener {

    private final SurvivalArena SurvivalArena;
    private final Player1Controller player1;
    private final BotController bot;
    private final SurvivalViewer SurvivalViewer;
    private Gui.ACTION player1Movement = Gui.ACTION.RIGHT;

    public SurvivalController(GameState gameState, Gui gui) {
        super(gameState, gui);
        SurvivalArena = new SurvivalArena(gameState.getGame().getWidth(), gameState.getGame().getHeight());
        player1 = new Player1Controller(SurvivalArena.getPlayer1());
        SurvivalViewer = new SurvivalViewer(gui, gameState.getButtons(), SurvivalArena);
        bot = new BotController(SurvivalArena.getBot());
    }

    @Override
    public void step() throws IOException {
        getNextState();
        SurvivalViewer.draw();
        action();
        movePlayer1(player1Movement);

    }

    @Override
    public void getNextState() {
        if(!player1.getCar().isAlive()) {
            boolean classicGame = false;
            boolean survivalGame= true;
            nextState = new GameWinState(gameState.getGame(), gui, classicGame,survivalGame,"Score: "+SurvivalArena.getScore() );
            gameState.changeState(nextState);
        }
        else if(!bot.getCar().isAlive()){
            bot.setCar(SurvivalArena.botDied());
        }
    }

    public void movePlayer1(Gui.ACTION action) {
        Position currentPosition = player1.getCar().getPosition();
        Position nextPosition = player1.makeMovement(action);

        if(!player1.getCar().collisionWithOwnTrail() && !SurvivalArena.wallCollision() && !SurvivalArena.playerCollisionWithBotTrail()) {
            player1.getCar().getTrailList().add(new Trail(currentPosition, "#FFFF00"));
            if(!SurvivalArena.enterPortalThroughStart(action, player1) && !SurvivalArena.enterPortalThroughExit(action, player1)) {
                player1.moveCar(nextPosition);
            }
        }
    }

    public void action() {
        Position botCurrentPosition = bot.getCar().getPosition();
        List<Integer> possibleMoves= new ArrayList<>();
        Position botNextPosition;

        if(SurvivalArena.upClearPosition(botCurrentPosition)&& SurvivalArena.getBot().getPreviousMovement()!=Gui.ACTION.DOWN){
            if(SurvivalArena.getBot().getPreviousMovement()==Gui.ACTION.UP){
                for(int i = 0; i<20;i++) {
                    possibleMoves.add(1);
                }
            }
            possibleMoves.add(1);
        }
        if (SurvivalArena.downClearPosition(botCurrentPosition)&& SurvivalArena.getBot().getPreviousMovement()!=Gui.ACTION.UP){
            if(SurvivalArena.getBot().getPreviousMovement()==Gui.ACTION.DOWN){
                for(int i = 0; i<20;i++) {
                    possibleMoves.add(2);
                }
            }
            possibleMoves.add(2);
        }
        if (SurvivalArena.leftClearPosition(botCurrentPosition)&& SurvivalArena.getBot().getPreviousMovement()!=Gui.ACTION.RIGHT){
            if(SurvivalArena.getBot().getPreviousMovement()==Gui.ACTION.LEFT){
                for(int i = 0; i<20;i++) {
                    possibleMoves.add(3);
                }
            }
            possibleMoves.add(3);
        }
        if (SurvivalArena.rightClearPosition(botCurrentPosition)&& SurvivalArena.getBot().getPreviousMovement()!=Gui.ACTION.LEFT){
            if(SurvivalArena.getBot().getPreviousMovement()==Gui.ACTION.RIGHT) {
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
        if (!bot.getCar().collisionWithOwnTrail() && !SurvivalArena.wallCollision() && !SurvivalArena.botCollisionWithCarTrail()) {
            bot.getCar().getTrailList().add(new Trail(botCurrentPosition, "#FF0000"));
            if(!SurvivalArena.enterPortalThroughStart(SurvivalArena.getBot().getPreviousMovement(), bot) && !SurvivalArena.enterPortalThroughExit(SurvivalArena.getBot().getPreviousMovement(), bot)){
                bot.moveCar(botNextPosition);
            }
        }
    }

    public Position botMove(int move, Position initialPosition) {
        Position finalPosition;
        if ( move ==1){
            finalPosition= initialPosition.moveUp(1);
            SurvivalArena.getBot().setPreviousMovement(Gui.ACTION.UP);
        }
        else if (move==2){
            finalPosition= initialPosition.moveDown(1);
            SurvivalArena.getBot().setPreviousMovement(Gui.ACTION.DOWN);
        }
        else if (move==3){
            finalPosition= initialPosition.moveLeft(1);
            SurvivalArena.getBot().setPreviousMovement(Gui.ACTION.LEFT);
        }
        else if (move==4){
            finalPosition=initialPosition.moveRight(1);
            SurvivalArena.getBot().setPreviousMovement(Gui.ACTION.RIGHT);
        }
        else finalPosition=initialPosition.moveUp(1);
        return finalPosition;
    }

    @Override
    public void keyPressed(Gui.ACTION action) {
        if(action == Gui.ACTION.UP || action == Gui.ACTION.RIGHT || action == Gui.ACTION.LEFT || action == Gui.ACTION.DOWN)
            player1Movement = action;
        else if(action == Gui.ACTION.PAUSE)
            gameState.changeState(new PauseState(gameState.getGame(), gui));
    }
}
