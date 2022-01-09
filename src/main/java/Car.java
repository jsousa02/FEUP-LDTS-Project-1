import com.googlecode.lanterna.*;;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Car extends Position {
    public String str;
    public String color;

    public Car(int x, int y, String str, String color) {
        super(x, y);
        this.str = str;
        this.color = color;
    }

    /**
     * @brief Draws the car
     * @param graphics
     */
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()),str);
    }

    /**
     * @return The position after the player moves up
     */
    public Position moveUp() {
        return new Position(getX(), getY() - 1);
    }

    /**
     * @return The position after the player moves down
     */
    public Position moveDown() {
        return new Position(getX(), getY() + 1);
    }

    /**
     * @return The position after the player moves right
     */
    public Position moveRight() {
        return new Position(getX() +1, getY());
    }

    /**
     * @return The position after the player moves left
     */
    public Position moveLeft() {
        return new Position(getX() -1, getY());
    }

    /**
     * Sets the car character representation
     * @param str The new character
     */
    public void setString(String str){
        this.str = str;
    }
}
