import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private int width;
    private int height;

    private List<Wall> walls = new ArrayList<>();

    public Arena(int x, int y) {
        this.width = x;
        this.height = y;
        createWalls();
    }

    /**
     * @return the arena's width
     */
    public int getWidth() { return width; }

    /**
     * @return the arena's height
     */
    public int getHeight() { return height; }

    /**
     * @brief draws the arena and its components
     * @param graphics
     */
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall: walls)
            wall.draw(graphics);
    }

    private void createWalls() {
        for (int i = 0; i < width; i++) {
            walls.add(new Wall(i, 0));
            walls.add(new Wall(i, height - 1));
            walls.add(new Wall(0, i));
            walls.add(new Wall(width - 1, i));
        }
    }
}
