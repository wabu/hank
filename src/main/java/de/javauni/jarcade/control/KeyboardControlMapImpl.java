package de.javauni.jarcade.control;


/**
 * @author Michael Kmoch, Simon Lang
 */

import com.google.inject.Singleton;
import java.util.HashMap;

import de.javauni.jarcade.utils.Pair;

@Singleton
public class KeyboardControlMapImpl extends HashMap<Integer, Pair<Integer,ControlEvent>> implements KeyboardControlMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5262297185696935710L;


	
}
