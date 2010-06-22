package de.javauni.jarcade.view.gui;

import java.awt.Container;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.javauni.jarcade.control.ControlModule;

import de.javauni.yarrish.MainModelModule;

public class KeyboardInputTest {

    private Injector inj;
    
    @Before
    public void setUp() throws Exception {
        inj = Guice.createInjector(new ControlModule(), new MainModelModule());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testKeyboardInput() {
        // use an injector to create the object, not any contructor
        KeyboardInput input = inj.getInstance(KeyboardInput.class);
        input.keyPressed(new KeyEvent(new Container(), 0, 0, 0, KeyEvent.VK_UP, ' '));
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
