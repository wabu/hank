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
        /* injectors are only used to bootstrap the app, 
         * they are never ever created locally, as we can't confiure them 
         * form outside the class anymore
        Injector inj = Guice.createInjector(new ControlModule());
        keyboardControl = inj.getInstance(KeyboardControl.class);
         */
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
