package com.g0301.model;

public class TwoPlayerArena extends Arena {

    private Player player1 = new Player(new Position(20, 30), "#FF0000");
    private Player player2 = new Player(new Position(60, 30), "#FFFFFF");

    /**
     * @param width  The arena's width
     * @param height The arena's height
     * @brief Initializes the arena and the walls that delimit it
     */
    public TwoPlayerArena(int width, int height) {
        super(width, height);
        createPortals();
        createWalls();
    }

    /**
     * @return The car of the arena
     */
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    /**
     * @return Inspects if the player crash (true) into a wall and if he does so dies
     */
    @Override
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
}
