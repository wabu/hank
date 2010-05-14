package de.javauni.jarcade.view.gui;


/**
 * @author Michael Kmoch, Simon Lang
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.javauni.jarcade.control.ControlModule;
import de.javauni.jarcade.control.KeyboardControl;

public class KeyboardInput extends KeyAdapter{
	
	private final KeyboardControl keyboardControl;
	
	public KeyboardInput() {
		Injector inj = Guice.createInjector(new ControlModule());
		keyboardControl = inj.getInstance(KeyboardControl.class);
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
