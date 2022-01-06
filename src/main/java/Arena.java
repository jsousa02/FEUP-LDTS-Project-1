import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private int width;
    private int height;

    Car car = new Car(10,10);
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
}
