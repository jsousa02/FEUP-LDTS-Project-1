import com.googlecode.lanterna.*;;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Car extends Position {
    public String str;
    public final static int  LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;

    private int direction = RIGHT;

    public Car(int x, int y, String str) {
        super(x, y);
        this.str = str;
    }

    /**
     * @brief Draws the car
     * @param graphics
     */
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#3AFF33"));
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

    public void setDirection(int direction){
        this.direction = direction;
    }

    public int getDirection() {return direction;}

    /**
     *
     * @param str
     * @return
     */
    public void setString(String str){
        this.str = str;
    }
}
