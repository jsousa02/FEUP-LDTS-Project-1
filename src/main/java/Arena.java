import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.*;

public class Arena {

    private int width;
    private int height;
    private Car bot = new Car(60,30,"X", "#FF0000");
    private Car car = new Car(2, 1, "→", "#3AFF33");
    private List<Wall> walls = new ArrayList<>();
    private ArrayList<Trail> trailList = new ArrayList<>();
    private ArrayList<Trail> botTrailList = new ArrayList<>();
    private Stack<String> previousMovement = new Stack<>();
    private Stack<Integer> previousBotMovement = new Stack<>();

    /**
     * @brief Initializes the arena and the walls that delimit it
     * @param x The arena's width
     * @param y The arena's height
     */
    public Arena(int x, int y) {
        this.width = x;
        this.height = y;
        createWalls();
        previousMovement.push("Right");
        previousBotMovement.push(0);
    }

    /**
     * @return The walls from the arena
     */
    public List<Wall> getWalls() { return walls; }

    /**
     * @return The arena's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The arena's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @brief Draws the arena and its components
     */
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        car.draw(graphics);
        bot.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Trail trail : trailList)
            trail.draw(graphics);
        for (Trail botTrail: botTrailList)
            botTrail.draw(graphics);
    }
    public Position getCarPosition(){
        return car.getPosition();
    }
    public List<Trail> getTrailList(){
        return trailList;
    }
    public List<Trail> getBotTrailList(){
        return botTrailList;
    }

    /**
     * @brief Create the walls
     */
    private void createWalls() {
        for (int i = 0; i < width; i++) {
            walls.add(new Wall(i, 0, "⊥"));
            walls.add(new Wall(i, height - 1, "⊤"));
            walls.add(new Wall(0, i, "⊣"));
            walls.add(new Wall(width - 1, i, "⊢"));
        }
    }

    /**
     * @param key The key to be processed
     * @brief Process a given input into the player movement
     */
    public void processKey(KeyStroke key) {
        botMovement();
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp -> {
                previousMovement.push("Up");
                moveCar(car.moveUp());
                car.setString("↑");
                trailList.add(new Trail(car.getPosition().getX(), car.getPosition().getY() + 1, "|", "#3AFF33"));
            }
            case ArrowDown -> {
                previousMovement.push("Down");
                moveCar(car.moveDown());
                car.setString("↓");
                trailList.add(new Trail(car.getPosition().getX(), car.getPosition().getY() - 1, "|", "#3AFF33"));
            }
            case ArrowLeft -> {
                previousMovement.push("Left");
                moveCar(car.moveLeft());
                car.setString("←");
                trailList.add(new Trail(car.getPosition().getX() + 1, car.getPosition().getY(), "⸺", "#3AFF33"));
            }
            case ArrowRight -> {
                previousMovement.push("Right");
                moveCar(car.moveRight());
                car.setString("→");
                trailList.add(new Trail(car.getPosition().getX() - 1, car.getPosition().getY(), "⸺", "#3AFF33"));
            }
            default -> moveContinuously(previousMovement.peek());
        }
    }

    /**
     * @param position The position to move the car to
     * @brief Move the player to a new position
     */
    public void moveCar(Position position) {
        car.setPosition(position);
    }

    public void moveBot(Position position) {
        bot.setPosition(position);
    }

    /**
     * @return Inspects if the player crash (true) into a wall and if he does so dies
     */
    public boolean wallCollision(){
        for(Wall wall : walls){
            if(wall.getPosition().equals(car.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
    }

    /**
     * @return Inspects if the bot crash (true) into a wall and if he does so dies
     */
    public boolean botWallCollision(){
        for(Wall wall : walls){
            if(wall.getPosition().equals(bot.getPosition())){
                System.out.println("WIN.");
                return true;
            }
        }
        return false;
    }

    /**
     * @return Inspects if the player crash (true) into a trail and if he does so dies
     */
    public boolean trailCollision(){
        for(Trail trail : trailList){
            for(Trail botTrail : botTrailList)
                if(trail.getPosition().equals(car.getPosition())
                        || botTrail.getPosition().equals(car.getPosition())){
                System.out.println("Death.");
                return true;
                }
        }
        return false;
    }

    /**
     * @return Inspects if the bot crash (true) into a trail and if he does so dies
     */
    public boolean botTrailCollision(){
        for(Trail trail : trailList)
        for (Trail botTrail : botTrailList){
                if(botTrail.getPosition().equals(bot.getPosition())
                        || trail.getPosition().equals(bot.getPosition())) {
                    System.out.println("WIN.");
                    return true;
                }
        }
        return false;
    }
    /**
     * @brief Moves the cars continuously until a valid key to change direction is pressed
     * @param move the previous move made by the car
     */
    public void moveContinuously(String move) {
        switch (move) {
            case "Right" -> {
                moveCar(new Position(car.getPosition().getX() + 1, car.getPosition().getY()));
                trailList.add(new Trail(car.getPosition().getX() - 1, car.getPosition().getY(), "⸺", "#3AFF33"));
            }
            case "Left" -> {
                moveCar(new Position(car.getPosition().getX() - 1, car.getPosition().getY()));
                trailList.add(new Trail(car.getPosition().getX() + 1, car.getPosition().getY(), "⸺", "#3AFF33"));
            }
            case "Up" -> {
                moveCar(new Position(car.getPosition().getX(), car.getPosition().getY() - 1));
                trailList.add(new Trail(car.getPosition().getX(), car.getPosition().getY() + 1, "|", "#3AFF33"));
            }
            case "Down" -> {
                moveCar(new Position(car.getPosition().getX(), car.getPosition().getY() + 1));
                trailList.add(new Trail(car.getPosition().getX(), car.getPosition().getY() - 1, "|", "#3AFF33"));
            }
        }
    }

    /**
     * @brief handles bot movement
     */
    public void botMovement() {
        int rand = new Random().nextInt(4);
        if(rand == botMovements.UP.getMovement() && previousBotMovement.peek() != 3){
            moveBot(bot.moveUp());
            previousBotMovement.push(2);
            botTrailList.add(new Trail(bot.getPosition().getX(), bot.getPosition().getY() + 1, "|", "#FF0000"));
        }
        else if (rand == botMovements.DOWN.getMovement() && previousBotMovement.peek() != 2) {
            moveBot(bot.moveDown());
            previousBotMovement.push(3);
            botTrailList.add(new Trail(bot.getPosition().getX(), bot.getPosition().getY() - 1, "|", "#FF0000"));
        }
        else if (rand == botMovements.LEFT.getMovement() && previousBotMovement.peek() != 1) {
            moveBot(bot.moveLeft());
            previousBotMovement.push(0);
            botTrailList.add(new Trail(bot.getPosition().getX() + 1, bot.getPosition().getY(), "⸺", "#FF0000"));
        }
        else if (rand == botMovements.RIGHT.getMovement() && previousBotMovement.peek() != 0) {
            moveBot(bot.moveRight());
            previousBotMovement.push(1);
            botTrailList.add(new Trail(bot.getPosition().getX() - 1, bot.getPosition().getY(), "⸺", "#FF0000"));
        }
    }

    public enum botMovements {
        LEFT (0),
        RIGHT (1),
        UP (2),
        DOWN(3);

        int valor;

        botMovements(int i){
             this.valor = i;
        }
        int getMovement() {
            return valor;
        }
    }
}
