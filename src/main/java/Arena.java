import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Arena {

    private int width;
    private int height;

    public Arena(int x, int y) {
        this.width = x;
        this.height = y;
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
     * @brief draws the arena and its components
     * @param graphics
     */
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

    }
}
