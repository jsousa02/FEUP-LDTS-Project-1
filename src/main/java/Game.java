import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena = new Arena(60, 60);
    private int x=10;
    private int y=10;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(60, 60);
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
    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }
    public void run() {
        try {
            while(true) {
                draw();
                com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q'))
                    screen.close();
                if (key.getKeyType() == KeyType.EOF)
                    break;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key){
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp    -> y--;
            case ArrowDown  -> y++;
            case ArrowLeft  -> x--;
            case ArrowRight -> x++;
        }
    }
}
