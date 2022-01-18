package com.g0301.model;

import com.g0301.Gui.Gui;

import java.util.Random;

public class Bot extends Car{
    public Bot(Position position, String color) {
        super(position, color);
    }
    public void botMovement() {
        int rand = new Random().nextInt(4);
        if(rand == 0){
            Gui.ACTION action= Gui.ACTION.LEFT;
        }
        else if (rand == 1){
            Gui.ACTION action= Gui.ACTION.RIGHT;
        }
        else if(rand == 2){
            Gui.ACTION action= Gui.ACTION.UP;
        }
        else if(rand == 3){
            Gui.ACTION action= Gui.ACTION.DOWN;
        }
    }
}