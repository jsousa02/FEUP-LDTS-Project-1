package com.g0301.gui;

import com.g0301.state.KeyboardListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardObserver extends KeyAdapter {
    private KeyboardListener listener;

    public KeyboardObserver() {}

    /**
     * @brief KeyListener to handle player input
     * @param event Pressed key by the player
     */
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
        }
    }

    /**
     * @brief Set a listener
     * @param listener The new listener to be set
     */
    public void setListener(KeyboardListener listener) {
        this.listener = listener;
    }
}
