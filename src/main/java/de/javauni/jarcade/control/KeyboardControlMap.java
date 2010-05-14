package de.javauni.jarcade.control;

/**
 * @author Michael Kmoch, Simon Lang
 */

import java.awt.event.KeyEvent;

import de.javauni.jarcade.utils.Pair;

public interface KeyboardControlMap {

	public Pair<Integer, ControlEvent> get(Object key);
	public Pair<Integer, ControlEvent> put(KeyEvent key,
			Pair<Integer, ControlEvent> value);
}
