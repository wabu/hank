package de.javauni.jarcade.control.playercontrol;

/**
 * @author Michael Kmoch, Simon Lang
 */

import de.javauni.jarcade.control.controlmanagement.ControlEvent;
import de.javauni.jarcade.utils.Pair;
import java.util.Set;

public interface PlayerControlMap {
	public Pair<Integer, ControlEvent> get(Object key);
	public Pair<Integer, ControlEvent> put(Integer key,
			Pair<Integer, ControlEvent> value);
        public Set<Integer> keySet();
}
