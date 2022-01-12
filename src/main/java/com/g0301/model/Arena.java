package com.g0301.model;

import java.util.*;

public class Arena {

    private int width;
    private int height;
    private Car car = new Car(new Position(20, 30), "#FF0000");
    private Car bot = new Car(new Position(100, 30), "#3AFF33");
    private final List<Wall> walls = new ArrayList<>();
    private ArrayList<Trail> trailList = new ArrayList<>();
    private ArrayList<Trail> botTrailList = new ArrayList<>();
    private Stack<String> previousMovement = new Stack<>();
    private Stack<Integer> previousBotMovement = new Stack<>();

    /**
     * @brief Initializes the arena and the walls that delimit it
     * @param width The arena's width
     * @param height The arena's height
     */
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        createWalls();
        previousMovement.push("Right");
        previousBotMovement.push(0);
    }

    /**
     * @return The car of the arena
     */
    public Car getCar() {
        return car;
    }

    public Car getBot() {
        return bot;
    }

    /**
     * @return The walls from the arena
     */
    public List<Wall> getWalls() { return walls; }

    /**
     * @return The arena's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The arena's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @brief Create the walls
     */
    private void createWalls() {
        for (int i = 0; i < width; i++) {
            walls.add(new Wall(new Position(i, 0), "#FFFFFF"));
            walls.add(new Wall(new Position(i, height - 1), "#FFFFFF"));
            walls.add(new Wall(new Position(0, i), "#FFFFFF"));
            walls.add(new Wall(new Position(width - 1, i), "#FFFFFF"));
        }
    }

    /**
     * @return Inspects if the player crash (true) into a wall and if he does so dies
     */
    public boolean wallCollision(){
        for(Wall wall : walls){
            if(wall.getPosition().equals(car.getPosition())){
                System.out.println("You lost");
                return true;
            }
            if (wall.getPosition().equals(bot.getPosition())) {
                System.out.println("You won");
                return true;
            }
        }
        return false;
    }

    /**
     * @return Inspects if the player crash (true) into a trail and if he does so dies
     */
    public boolean trailCollision(){
        for(Trail trail : trailList){
            for(Trail botTrail : botTrailList)
                if(trail.getPosition().equals(car.getPosition())
                        || botTrail.getPosition().equals(car.getPosition())){
                System.out.println("Death.");
                return true;
                }
        }
        return false;
    }

    /**
     * @return Inspects if the bot crash (true) into a trail and if he does so dies
     */
    public boolean botTrailCollision(){
        for(Trail trail : trailList)
        for (Trail botTrail : botTrailList){
                if(botTrail.getPosition().equals(bot.getPosition())
                        || trail.getPosition().equals(bot.getPosition())) {
                    System.out.println("WIN.");
                    return true;
                }
        }
        return false;
    }
}
