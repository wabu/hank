package de.javauni.jarcade.control;

import java.awt.event.KeyEvent;

import de.javauni.jarcade.model.control.CharacterControl;

/**
 * @author Michael Kmoch, Simon Lang
 */


public interface KeyboardControl {
    public void registerControl(CharacterControl ctl, int playerNo);
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
}
