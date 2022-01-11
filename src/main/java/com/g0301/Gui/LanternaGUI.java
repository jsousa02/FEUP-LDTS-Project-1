package com.g0301.Gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.g0301.model.Position;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class LanternaGUI implements Gui {
    private final TerminalScreen screen;
    private final int width;
    private final int height;

    public LanternaGUI(int width, int height) throws IOException, FontFormatException {
        AWTTerminalFontConfiguration fontConfiguration = loadTronFont();
        Terminal terminal = createTerminal(width, height, fontConfiguration);
        this.screen = createScreen(terminal);
        this.height = height;
        this.width = width;
        addCloseScreenListener();
    }

    public TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen terminalScreen = new TerminalScreen(terminal);

        terminalScreen.setCursorPosition(null);
        terminalScreen.startScreen();
        terminalScreen.doResizeIfNecessary();

        return terminalScreen;
    }

    public Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfiguration) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfiguration);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    public AWTTerminalFontConfiguration loadTronFont() throws IOException, FontFormatException {
        File tronFontFile = new File("src/main/resources/fonts/square.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, tronFontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 10);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    @Override
    public TextGraphics createTextGraphics() {
        return screen.newTextGraphics();
    }

    @Override
    public boolean isActive() {
        return ((AWTTerminalFrame) screen.getTerminal()).isDisplayable();
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void drawBackground(TextGraphics textGraphics, String color) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(color));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    }

    @Override
    public void drawCar(Position position, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        drawElement(graphics, position, color, "C");
    }

    @Override
    public void drawElement(TextGraphics graphics, Position position, String color, String text) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void drawWall(Position position, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        drawElement(graphics, position, color, "*");
    }

    @Override
    public void drawTrail(Position position, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        drawElement(graphics, position, color, "-");
    }

    public TerminalScreen getScreen() {
        return screen;
    }

    private void addCloseScreenListener() {
        ((AWTTerminalFrame) screen.getTerminal()).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ((AWTTerminalFrame) screen.getTerminal()).dispose();
                System.exit(0);
            }
        });
    }

    public void addKeyboardListener(KeyboardObserver observer) {
        ((AWTTerminalFrame) screen.getTerminal()).getComponent(0).addKeyListener(observer);
    }
}
