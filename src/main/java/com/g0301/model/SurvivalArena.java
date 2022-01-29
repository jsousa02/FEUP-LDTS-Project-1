package com.g0301.model;

import java.util.Random;

public class SurvivalArena extends Arena {
    private Player player1 = new Player(new Position(20, 30), "#FF0000");
    private Bot bot = new Bot(new Position(60, 30), "#00FF00");
    int score;

    public SurvivalArena(int width, int height) {
        super(width, height);
        createWalls();
        createPortals();
    }

    public boolean wallCollision() {
        for(Wall wall: walls) {
            if(wall.getPosition().equals(player1.getPosition())) {
                player1.setDead();
                return true;
            }
            if(wall.getPosition().equals(bot.getPosition())) {
                bot.setDead();
                return true;
            }
        }
        return false;
    }

    public boolean botCollisionWithCarTrail() {
        for (Trail trail : player1.getTrailList()) {
            if (bot.getPosition().equals(trail.getPosition())) {
                bot.setDead();
                return true;
            }
        }
        return false;
    }

    public boolean playerCollisionWithBotTrail() {
        for (Trail trail : bot.getTrailList()) {
            if (player1.getPosition().equals(trail.getPosition())) {
                player1.setDead();
                return true;
            }
        }
        return false;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Bot getBot() {
        return bot;
    }

    public boolean upClearPosition(Position position){
        if (bot.getTrailList().isEmpty()){
            return true;
        }
        for (Trail trail: bot.getTrailList()){
            if (position.moveUp(1).equals(trail.getPosition())){
                return false;
            }
        }
        for (Trail trail1: player1.getTrailList()){
            if(position.moveUp(1).equals(trail1.getPosition())){
                return false;
            }
        }
        for (Wall wall: walls){
            if(position.moveUp(1).equals(wall.getPosition())){
                return false;
            }
        }
        return true;
    }
    public boolean downClearPosition(Position position){
        for (Trail trail: bot.getTrailList()) {
            if (position.moveDown(1).equals(trail.getPosition())) {
                return false;
            }
        }

        for (Trail trail1: player1.getTrailList()){
            if(position.moveDown(1).equals(trail1.getPosition())){
                return false;
            }
        }
        for (Wall wall: walls){
            if(position.moveDown(1).equals(wall.getPosition())){
                return false;
            }
        }
        return true;
    }
    public boolean leftClearPosition(Position position){
        for (Trail trail: bot.getTrailList()){
            if (position.moveLeft(1).equals(trail.getPosition())){
                return false;
            }
        }
        for (Trail trail1: player1.getTrailList()){
            if(position.moveLeft(1).equals(trail1.getPosition())){
                return false;
            }
        }
        for (Wall wall: walls){
            if(position.moveLeft(1).equals(wall.getPosition())){
                return false;
            }
        }
        return true;
    }
    public boolean rightClearPosition(Position position){
        for (Trail trail: bot.getTrailList()){
            if (position.moveRight(1).equals(trail.getPosition())){
                return false;
            }
        }
        for (Trail trail1: player1.getTrailList()){
            if(position.moveRight(1).equals(trail1.getPosition())){
                return false;
            }
        }
        for (Wall wall: walls){
            if(position.moveRight(1).equals(wall.getPosition())){
                return false;
            }
        }
        return true;
    }
    public Car botDied(){
        int rand = new Random().nextInt(80);
        int rand1 = new Random().nextInt(50);
        score++;
        return bot= new Bot(new Position(rand,rand1),"#00FF00");
    }
    public String getScore(){
        String score_string= String.valueOf(score);
        return score_string;
    }
}

