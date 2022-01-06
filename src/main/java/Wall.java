import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Position {

    public Wall(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics, String str) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(x, y), str);
    }

    /*public int getPosition(){
        return getPosition();
    }*/

}
