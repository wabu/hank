package de.javauni.jarcade.view.gui;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.javauni.jarcade.control.ControlModule;

public class KeyboardInputTest {

	private Injector inj;
	
	@Before
	public void setUp() throws Exception {
	    inj = Guice.createInjector(new ControlModule());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testKeyboardInput() {
	    // use an injector to create the object, not any contructor
	    KeyboardInput input = inj.getInstance(KeyboardInput.class);
	    input.keyPressed(new KeyEvent(null, 0, 0, 0, KeyEvent.VK_UP, ' '));
	}

	@Test
	public void testKeyPressedKeyEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testKeyReleasedKeyEvent() {
		fail("Not yet implemented");
	}

}
