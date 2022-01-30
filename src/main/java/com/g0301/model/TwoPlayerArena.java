package com.g0301.model;

public class TwoPlayerArena extends Arena {

    private Player player1 = new Player(new Position(20, 30), "#FF0000");
    private Player player2 = new Player(new Position(60, 30), "#FFFFFF");
    private BoostBar player1BoostBar = new BoostBar(new Position(3, 59), "#00FF00");
    private BoostBar player2BoostBar = new BoostBar(new Position(77, 59), "#E20062");

    public TwoPlayerArena(int width, int height) {
        super(width, height);
        createPortals();
        createWalls();
        player1BoostBar.createBoostBar();
        player2BoostBar.createBoostBar();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean wallCollision() {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(player1.getPosition())) {
                player1.setDead();
                return true;
            }
            if (wall.getPosition().equals(player2.getPosition())) {
                player2.setDead();
                return true;
            }
        }
        return false;
    }


    public boolean player1CollidesWithPlayer2() {
        for(Trail trail: player2.getTrailList()) {
            if(player1.getPosition().equals(trail.getPosition())) {
                player1.setDead();
                return true;
            }
        }
        return false;
    }

    public boolean player2CollidesWithPlayer1() {
        for(Trail trail: player1.getTrailList()) {
            if(player2.getPosition().equals(trail.getPosition())) {
                player2.setDead();
                return true;
            }
        }
        return false;
    }

    public BoostBar getPlayer1BoostBar() {
        return player1BoostBar;
    }

    public BoostBar getPlayer2BoostBar() {
        return player2BoostBar;
    }
}
