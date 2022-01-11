package com.g0301;

import java.awt.*;
import java.io.IOException;

class GameStart {
    public static void main(String[] args) throws IOException, FontFormatException {
        Game game = Game.getInstance();
        game.run();
    }
}