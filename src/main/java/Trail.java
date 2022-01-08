import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class Trail extends Position{
    public String str ;
    Trail(int x, int y, String str){
        super(x,y);
        this.str = str;
    }

    /**
     * @brief Draws the trails of a car
     * @param graphics
     */
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#3AFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()),str);
    }

}
