package com.g0301.Gui;

import com.g0301.state.KeyboardListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardObserver extends KeyAdapter {
    private KeyboardListener listener;

    public KeyboardObserver() {}

    public void keyPressed(KeyEvent event) {
        switch(event.getKeyCode()) {
            case KeyEvent.VK_W:
                listener.keyPressed(Gui.ACTION.UP);
                break;
            case KeyEvent.VK_A:
                listener.keyPressed(Gui.ACTION.LEFT);
                break;
            case KeyEvent.VK_S:
                listener.keyPressed(Gui.ACTION.DOWN);
                break;
            case KeyEvent.VK_D:
                listener.keyPressed(Gui.ACTION.RIGHT);
                break;
        }
    }

    public void setListener(KeyboardListener listener) {
        this.listener = listener;
    }
}
