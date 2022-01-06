//package model;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
//import model.Car;
//import model.Position;


import java.util.ArrayList;
import java.util.List;

public class Arena {

    private int width;
    private int height;

    Car car = new Car(2,1);
    private List<Wall> walls = new ArrayList<>();

    public Arena(int x, int y) {
        this.width = x;
        this.height = y;
        createWalls();
    }

    /**
     * @return the arena's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the arena's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param graphics
     * @brief draws the arena and its components
     */
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        car.draw(graphics, "→");
        for (Wall wall : walls)
            wall.draw(graphics, "⧫");
    }

    private void createWalls() {
        for (int i = 0; i < width; i++) {
            walls.add(new Wall(i, 0));
            walls.add(new Wall(i, height - 1));
            walls.add(new Wall(0, i));
            walls.add(new Wall(width - 1, i));
        }
    }

    public void processKey(KeyStroke key) {
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp:
                moveCar(car.moveUp());
                break;
            case ArrowDown:
                moveCar(car.moveDown());
                break;
            case ArrowLeft:
                moveCar(car.moveLeft());
                break;
            case ArrowRight:
                moveCar(car.moveRight());
                break;
        }
    }

    public void moveCar(Position position) {
        if (canPlayerMove(position)) {
            car.setPosition(position);
            /*if (car.moveUp().equals(position)) {
                car.draw(graphics, "↑");
            } else if (car.moveDown().equals(position)) {
                car.draw(graphics, "↓");
            } else if (car.moveLeft().equals(position)) {
                car.draw(graphics, "←");
            } else if (car.moveRight().equals(position)) {
                car.draw(graphics, "→");
            }*/
        }
    }
    private boolean canPlayerMove(Position pos){
        return (pos.getX() >= 0 && pos.getX() < width) &&
                (pos.getY() >= 0 && pos.getY() < height);//&&
                //!walls.contains(new Wall(pos.getX(), pos.getY()));
    }
    /*
    boolean canPlayerMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        if (position.getX() > width || position.getX() < 0) {
            return false;
        } else if (position.getY() > height || position.getY() < 0) {
            return false;
        }
        else return true;
    }

    public boolean Wall_Collision(){
        for(Wall wall : walls){
            if(wall.getX()==car.getX() && wall.getY() == car.getY()){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
    }*/

    public boolean Wall_Collision() {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(car.getPosition())) {
                System.out.println("Death.");
                return true;
            }
        }
        return false;
    }
}
