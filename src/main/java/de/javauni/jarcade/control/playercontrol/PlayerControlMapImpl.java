package de.javauni.jarcade.control.playercontrol;


/**
 * @author Michael Kmoch, Simon Lang
 */

import com.google.inject.Singleton;
import java.util.HashMap;

import de.javauni.jarcade.control.controlmanagement.ControlEvent;
import de.javauni.jarcade.utils.Pair;

@Singleton
public class PlayerControlMapImpl extends HashMap<Integer, Pair<Integer,ControlEvent>> implements PlayerControlMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5262297185696935710L;


	
}
