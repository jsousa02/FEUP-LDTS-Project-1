package com.g0301.model;

public class OnePlayerArena extends Arena {

    private Player player1 = new Player(new Position(20, 30), "#FF0000");
    private Bot bot = new Bot(new Position(60, 30), "#00FF00");

    public OnePlayerArena(int width, int height) {
        super(width, height);
        createWalls();
        createPortals();
    }

    @Override
    public boolean wallCollision() {
        for(Wall wall: walls) {
            if(wall.getPosition().equals(player1.getPosition())) {
                player1.setDead();
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
            if (position.moveUp().equals(trail.getPosition())){
                return false;
            }
        }
        return true;
    }
    public boolean downClearPosition(Position position){
        for (Trail trail: bot.getTrailList()){
            if (position.moveDown().equals(trail.getPosition())){
                return false;
            }
        }
        return true;
    }
    public boolean leftClearPosition(Position position){
        for (Trail trail: bot.getTrailList()){
            if (position.moveLeft().equals(trail.getPosition())){
                return false;
            }
        }
        return true;
    }
    public boolean rightClearPosition(Position position){
        for (Trail trail: bot.getTrailList()){
            if (position.moveRight().equals(trail.getPosition())){
                return false;
            }
        }
        return true;
    }
}
