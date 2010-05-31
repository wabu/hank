package de.javauni.jarcade.view.gui;

/**
 * @author Michael Kmoch, Simon Lang
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.google.inject.Inject;

import de.javauni.jarcade.control.KeyboardControl;

public class KeyboardInput extends KeyAdapter {

    private final KeyboardControl keyboardControl;

    @Inject
    public KeyboardInput(KeyboardControl keyboardControl) {
        this.keyboardControl = keyboardControl;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyboardControl.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyboardControl.keyReleased(e);
    }

}
