import com.googlecode.lanterna.*;;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Car extends Position{
    public Car(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics, String str) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#3AFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()),str);
    }

    public Position moveUp() {
        return new Position(getX(), getY() - 1);
    }
    public Position moveDown() {
        return new Position(getX(), getY() + 1);
    }
    public Position moveRight() {
        return new Position(getX() +1, getY());
    }
    public Position moveLeft() {
        return new Position(getX() -1, getY());
    }

}
