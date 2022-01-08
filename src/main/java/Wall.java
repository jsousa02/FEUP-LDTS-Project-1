import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Position {
    String str;
    public Wall(int x, int y, String str) {
        super(x, y);
        this.str = str;
    }

    /**
     * @brief Draws the walls that delimit the arena
     * @param graphics
     */
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(x, y), str);
    }
}
