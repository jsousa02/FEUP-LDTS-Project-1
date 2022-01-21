package com.g0301.model;

import com.g0301.controller.CarController;
import com.g0301.gui.Gui;

import java.util.+;

public abstract class Arena {

    protected int height;
    protected int width;
    protected List<Wall> walls = new ArrayList<>();
    protected List<Portal> portals = new ArrayList<>();

    public Arena(int width, int height) {
        this.height = height;
        this.width = width;
        createPortals();
    }

    protected void createPortals() {
        portals.add(new Portal(new Position(50, 30), new Position(10, 30), "#FF00FF"));
        portals.add(new Portal(new Position(35, 55), new Position(40, 10), "#FF0000"));
        portals.add(new Portal(new Position(10, 55), new Position(50, 50), "#00FFFF"));
    }

    protected void createWalls() {
        for (int i = 0; i < width; i++) {
            walls.add(new Wall(new Position(i, 0), "#FFFFFF"));
            walls.add(new Wall(new Position(i, height - 1), "#FFFFFF"));
            walls.add(new Wall(new Position(0, i), "#FFFFFF"));
            walls.add(new Wall(new Position(width - 1, i), "#FFFFFF"));
        }
    }

    /**
     * @return The walls from the arena
     */
    public List<Wall> getWalls() {
        return walls;
    }

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
            walls.add(new Wall(new Position(i, 0), "#F34256"));
            walls.add(new Wall(new Position(i, height - 1), "#F34256"));
            walls.add(new Wall(new Position(0, i), "#F34256"));
            walls.add(new Wall(new Position(width - 1, i), "#F34256"));
        }
    }

    /**
     * @return Inspects if the player crash (true) into a wall and if he does so dies
     */
    public boolean wallCollision() {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(car.getPosition())) {
                car.setDead();
                return true;
            }
            if (wall.getPosition().equals(bot.getPosition())) {
                return true;
            }
        }
        return false;
    }

    public void createPortals() {
        portals.add(new Portal(new Position(50, 30), new Position(10, 30), "#FF00FF"));
        portals.add(new Portal(new Position(35, 55), new Position(40, 10), "#FF0000"));
        portals.add(new Portal(new Position(10, 55), new Position(50, 50), "#00FFFF"));
    }


    public List<Portal> getPortals() {
        return portals;
    }

    public boolean enterPortalThroughStart(Gui.ACTION action, CarController controller) {
        for (Portal portal : portals) {
            if (controller.getCar().getPosition().equals(portal.getPosition())) {
                controller.getCar().setPosition(portal.getSecondPosition());
                controller.getCar().setPosition(controller.makeMovement(action));
                return true;
            }
        }
        return false;
    }

    public boolean enterPortalThroughExit(Gui.ACTION action, CarController controller) {
        for (Portal portal : portals) {
            if (controller.getCar().getPosition().equals(portal.getSecondPosition())) {
                controller.getCar().setPosition(portal.getPosition());
                controller.getCar().setPosition(controller.makeMovement(action));
                return true;
            }
        }
        return false;
    }

    public abstract boolean wallCollision();
}
