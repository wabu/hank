package de.javauni.jarcade.control;

/**
 * @author Michael Kmoch, Simon Lang
 */

import de.javauni.jarcade.utils.Pair;
import java.util.Set;

public interface KeyboardControlMap {
	public Pair<Integer, ControlEvent> get(Object key);
	public Pair<Integer, ControlEvent> put(Integer key,
			Pair<Integer, ControlEvent> value);
        public Set<Integer> keySet();
}
