package de.javauni.jarcade.view.gui;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.javauni.jarcade.control.ControlModule;
import de.javauni.jarcade.control.KeyboardControlMap;

public class KeyboardInputTest {

	private KeyboardControlMap kbdMap;
	
	@Before
	public void setUp() throws Exception {
		Injector inj = Guice.createInjector(new ControlModule());
		kbdMap = inj.getInstance(KeyboardControlMap.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testKeyboardInput() {
		KeyboardInput input = new KeyboardInput();
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
