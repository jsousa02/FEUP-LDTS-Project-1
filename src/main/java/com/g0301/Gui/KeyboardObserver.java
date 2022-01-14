package com.g0301.Gui;

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
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                listener.keyReleased(Gui.ACTION.UP);
                break;
            case KeyEvent.VK_A:
                listener.keyReleased(Gui.ACTION.LEFT);
                break;
            case KeyEvent.VK_S:
                listener.keyReleased(Gui.ACTION.DOWN);
                break;
            case KeyEvent.VK_D:
                listener.keyReleased(Gui.ACTION.RIGHT);
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
