package de.javauni.jarcade.control;

/**
 * @author Michael Kmoch, Simon Lang
 */

import java.awt.event.KeyEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.javauni.jarcade.utils.Pair;

public class KeyboardControlImpl implements KeyboardControl {
	
	private final Logger log = LoggerFactory.getLogger(KeyboardControlImpl.class);
	private KeyboardControlMap kbdMap;
	
	@Inject
	public KeyboardControlImpl() {
		Injector inj = Guice.createInjector(new ControlModule());
		kbdMap = inj.getInstance(KeyboardControlMap.class);
	}
	public void keyPressed(KeyEvent e)
	{
		Pair<Integer, ControlEvent> event = kbdMap.get(e);
		
		switch (event.snd)
		{
		case Jump:
			log.info("Jump key pressed");
			break;
		case MoveLeft:
			log.info("MoveLeft key pressed");
			break;
		case MoveRight:
			log.info("MoveRight key pressed");
			break;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		Pair<Integer, ControlEvent> event = kbdMap.get(e);
		
		switch (event.snd)
		{
		case Stop:
			log.info("Key released");
			break;
		}
		
	}
}
