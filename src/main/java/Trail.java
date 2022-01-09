import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Trail extends Position{
    public String str;
    public String color;
    Trail(int x, int y, String str, String color){
        super(x,y);
        this.str = str;
        this.color = color;
    }

    /**
     * @brief Draws the trails of a car
     * @param graphics
     */
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()),str);
    }

}
