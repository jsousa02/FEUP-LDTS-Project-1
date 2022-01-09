import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.Escape;


public class Game {

    private Screen screen;
    private Arena arena = new Arena(120, 60);

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(120, 60);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Draws all the components of the game
     */
    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    /**
     * @brief Runs the game
     */
    public void run() throws InterruptedException {
        try {
            while(true) {
                draw();
                KeyStroke key = new KeyStroke(KeyType.ArrowRight);
                Thread.sleep(75);
                KeyStroke temp = screen.pollInput();
                if (temp == null)
                        arena.processKey(new KeyStroke(KeyType.Backspace));
                else {
                    key = temp;
                    arena.processKey(key);
                }
                if(arena.wallCollision() || arena.trailCollision()
                        || arena.botTrailCollision() || arena.botWallCollision()) {
                    screen.close();
                    break;
                }
                if (key.getKeyType() == KeyType.Character &&
                        (key.getCharacter() == ('q') || key.getCharacter() == ('Q'))
                        || key.getKeyType() == Escape)
                    screen.close();
                if (key.getKeyType() == KeyType.EOF)
                    break;
                }

            } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
