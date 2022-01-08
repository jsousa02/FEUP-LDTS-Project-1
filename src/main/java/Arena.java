//package model;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
//import model.Car;
//import model.Position;

import java.util.*;

public class Arena {

    private int width;
    private int height;
    private double inicial_speed = 0.1;
    private int delay = 100;

    private Car car = new Car(2, 1, "→");
    private List<Wall> walls = new ArrayList<>();
    private ArrayList<Trail> trail_list = new ArrayList<>();


    /**
     * @brief Initializes the arena and the walls that delimit it
     * @param x The arena's width
     * @param y The arena's height
     */
    public Arena(int x, int y) {
        this.width = x;
        this.height = y;
        createWalls();
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
     * @param graphics
     * @brief Draws the arena and its components
     */
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        car.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Trail trail : trail_list)
            trail.draw(graphics);
    }
    public Position getCarPosition(){
        return car.getPosition();
    }
    public List<Trail> getTrailList(){
        return trail_list;
    }

    /**
     * @brief Create the walls
     */
    private void createWalls() {
        for (int i = 0; i < width; i++) {
            walls.add(new Wall(i, 0, "⊥"));
            walls.add(new Wall(i, height - 1, "⊤"));
            walls.add(new Wall(0, i, "⊣"));
            walls.add(new Wall(width - 1, i, "⊢"));
        }
    }

    /**
     * @param key The key to be processed
     * @brief Process a given input into the player movement
     */
    public void processKey(KeyStroke key) {
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp:
                moveCar(car.moveUp());
                car.setString("↑");
                trail_list.add(new Trail(car.getPosition().getX(), car.getPosition().getY()+1, "|"));
                break;
            case ArrowDown:
                moveCar(car.moveDown());
                car.setString("↓");
                trail_list.add(new Trail(car.getPosition().getX(), car.getPosition().getY()-1, "|"));
                break;
            case ArrowLeft:
                moveCar(car.moveLeft());
                car.setString("←");
                trail_list.add(new Trail(car.getPosition().getX()+1, car.getPosition().getY(), "⸺"));
                break;
            case ArrowRight:
                moveCar(car.moveRight());
                car.setString("→");
                trail_list.add(new Trail(car.getPosition().getX()-1, car.getPosition().getY(), "⸺"));
                break;
        }
    }

    /**
     * @param position The position to move the car to
     * @brief Move the player to a new position
     */
    public void moveCar(Position position) {
        car.setPosition(position);
    }

    /**
     * @return Inspects if the player crash (true) into a wall and if he does so dies
     */
    public boolean wall_Collision(){
        for(Wall wall : walls){
            if(wall.getPosition().equals(car.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
    }

    /**
     * @return Inspects if the player crash (true) into a trail and if he does so dies
     */
    public boolean trail_Collision(){
        for(Trail trail : trail_list){
            if(trail.getPosition().equals(car.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
    }

}
