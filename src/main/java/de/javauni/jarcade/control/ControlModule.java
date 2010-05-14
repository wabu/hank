package de.javauni.jarcade.control;

/**
 * @author Michael Kmoch, Simon Lang
 */

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class ControlModule implements Module{

	@Override
	public void configure(Binder binder) {
		binder.bind(KeyboardControl.class).annotatedWith(
				Names.named("KeyboardControl")).to(KeyboardControlImpl.class);
		binder.bind(KeyboardControlMap.class).annotatedWith(
				Names.named("KeyboardControlMap")).to(KeyboardControlMapImpl.class);
	}
	
}
