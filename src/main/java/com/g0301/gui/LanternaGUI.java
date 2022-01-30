package com.g0301.gui;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.terminal.swing.*;
import com.g0301.model.Position;

import java.awt.*;
import java.awt.event.*;
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

    public String getOperatingSystem(){
        String os = System.getProperty("os.name");
        return os;
    }

    public boolean isWindows(){
        return getOperatingSystem().startsWith("Windows");
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

        if(isWindows()){
            File tronFontFile = new File("src/main/resources/fonts/Square-Regular-Windows.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, tronFontFile);


            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font loadedFont = font.deriveFont(Font.PLAIN, 11);
            return AWTTerminalFontConfiguration.newInstance(loadedFont);
        }else {
            File tronFontFile = new File("src/main/resources/fonts/Square-Regular3.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, tronFontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font loadedFont = font.deriveFont(Font.PLAIN, 11);
            return AWTTerminalFontConfiguration.newInstance(loadedFont);
        }
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
    public void drawBackground(TextGraphics graphics, String color) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    }

    @Override
    public void drawCar(Position position, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        drawElement(graphics, position, color, "¦");
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
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        drawElement(graphics, position, color, "ª");
    }

    @Override
    public void drawTrail(Position position, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        drawElement(graphics, position, color, "Ò");
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

    @Override
    public void addKeyboardListener(KeyboardObserver observer) {
        ((AWTTerminalFrame) screen.getTerminal()).getComponent(0).addKeyListener(observer);
    }

    @Override
    public void drawButton(Position buttonPosition, String bColor, String fColor, String text, int width, int height) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(bColor));
        graphics.fillRectangle(new TerminalPosition(buttonPosition.getX() - width / 2 - 5, buttonPosition.getY() - height / 2), new TerminalSize(width + 10, height), ' ');
        drawText(graphics, new Position(buttonPosition.getX() - text.length() / 2, buttonPosition.getY()), fColor, text);
    }

    @Override
    public void drawText(TextGraphics graphics, Position position, String color, String text) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), text);
    }

    @Override
    public void drawLogo(String color, String textColor) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        drawText(graphics, new Position((width / 2) - 15, 3), textColor, "  ______                           ");
        drawText(graphics, new Position((width / 2) - 15, 4), textColor, " /_  __/________  ____    __    __ ");
        drawText(graphics, new Position((width / 2) - 15, 5), textColor, "  / / / ___/ __ \\/ __ \\__/ /___/ /_");
        drawText(graphics, new Position((width / 2) - 15, 6), textColor, " / / / /  / /_/ / / / /_  __/_  __/");
        drawText(graphics, new Position((width / 2) - 15, 7), textColor, "/_/ /_/   \\____/_/ /_/ /_/   /_/   ");
        drawText(graphics, new Position((width / 2) - 15, 8), textColor, "                                   ");
    }

    @Override
    public void drawPortal(Position startPosition, Position endPosition, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        drawElement(graphics, startPosition, color, "«");
        drawElement(graphics, endPosition, color, "«");
    }

    @Override
        public void drawInstructions() {
        TextGraphics graphics = screen.newTextGraphics();
        drawLogo("#000000", "#FF0000");
        drawText(graphics, new Position(10, 12), "#FFFFFF", "You can play 3 different game modes");
        drawText(graphics, new Position(12, 15), "#FFFFFF", "Single player classic");
        drawText(graphics, new Position(12, 17), "#FFFFFF", "1v1 with 2 players");
        drawText(graphics, new Position(12, 19), "#FFFFFF", "Survival mode");
        drawText(graphics, new Position(10, 22), "#FFFFFF", "Player 1 controls:");
        drawText(graphics, new Position(12, 25), "#FFFFFF", "Move the car with WASD and use boost with the space bar");
        drawText(graphics, new Position(10, 28), "#FFFFFF", "Player 2 controls:");
        drawText(graphics, new Position(12, 31), "#FFFFFF", "Move the car with the arrow keys and use boost with left shift");
        drawText(graphics, new Position(10, 34), "#FFFFFF", "You can't crash against the walls or against the other player's trails");
        drawText(graphics, new Position(10, 37), "#FFFFFF", "You can use the portals to teleport");
        drawText(graphics, new Position(10, 40), "#FFFFFF", "You can't enter the same portal twice");

    }

    @Override
    public void drawPlayer1BoostBar(Position position, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        drawText(graphics, new Position(3, 58), "#FFFFFF", "Boost");
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        drawElement(graphics, position, color, "*");
    }

    @Override
    public void drawPlayer2BoostBar(Position position, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        drawText(graphics, new Position(82, 58), "#FFFFFF", "Boost");
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        drawElement(graphics, position, color, "*");
    }
    @Override
    public void drawScore(String score){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        drawText(graphics, new Position(80, 59), "#FFFFFF", "Score:"+score);
    }
}
