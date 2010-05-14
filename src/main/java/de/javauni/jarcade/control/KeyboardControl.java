package de.javauni.jarcade.control;

/**
 * @author Michael Kmoch, Simon Lang
 */

import java.awt.event.KeyEvent;

public interface KeyboardControl {
	public void keyPressed(KeyEvent e);
	
	public void keyReleased(KeyEvent e);
}
