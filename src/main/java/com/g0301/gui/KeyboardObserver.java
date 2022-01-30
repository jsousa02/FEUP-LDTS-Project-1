package com.g0301.gui;

import com.g0301.state.KeyboardListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardObserver extends KeyAdapter {
    private KeyboardListener listener;

    public KeyboardObserver() {}

    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
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
            case KeyEvent.VK_ESCAPE:
                listener.keyPressed(Gui.ACTION.QUIT);
                break;
            case KeyEvent.VK_ENTER:
                listener.keyPressed(Gui.ACTION.ENTER);
                break;
            case KeyEvent.VK_P:
                listener.keyPressed(Gui.ACTION.PAUSE);
                break;
            case KeyEvent.VK_UP:
                listener.keyPressed(Gui.ACTION.P2UP);
                break;
            case KeyEvent.VK_DOWN:
                listener.keyPressed(Gui.ACTION.P2DOWN);
                break;
            case KeyEvent.VK_LEFT:
                listener.keyPressed(Gui.ACTION.P2LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                listener.keyPressed(Gui.ACTION.P2RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                listener.keyPressed(Gui.ACTION.P1BOOST);
                break;
            case KeyEvent.VK_SHIFT:
                listener.keyPressed(Gui.ACTION.P2BOOST);
                break;
        }
    }

    public void setListener(KeyboardListener listener) {
        this.listener = listener;
    }
}
