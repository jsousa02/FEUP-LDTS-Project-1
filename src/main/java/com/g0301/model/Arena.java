package com.g0301.model;

import java.util.*;

public class Arena {

    private int width;
    private int height;
    private Car car = new Car(new Position(20, 30), "#FF0000");
    private Car bot = new Car(new Position(100, 30), "#3AFF33");
    private final List<Wall> walls = new ArrayList<>();
    private final List<Portal> portals = new ArrayList<>();
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
        createPortals();
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
                //System.out.println("You lost");
                return true;
            }
            if (wall.getPosition().equals(bot.getPosition())) {
                System.out.println("You won");
                return true;
            }
        }
        return false;
    }

    public void createPortals() {
        for(int i = 0; i < 1; i++) {
            portals.add(new Portal(new Position(50, 30), new Position(10, 30), "#FF00FE"));
        }
    }

    public List<Portal> getPortals() {
        return portals;
    }

    public boolean enterPortalThroughStart() {
        for (Portal portal : portals) {
            if (car.getPosition().equals(portal.getPosition())) {
                car.setPosition(portal.getSecondPosition());
                System.out.println("entered through start");
                return true;
            }
        }
        return false;
    }

    public boolean enterPortalThroughExit() {
        for (Portal portal : portals) {
            if (car.getPosition().equals(portal.getSecondPosition())) {
                car.setPosition(portal.getPosition());
                System.out.println("entered through exit");
                return true;
            }
        }
        return false;
    }
}
