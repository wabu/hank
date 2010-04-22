package de.javauni.yarrish.model.space.viewport;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class ViewPortModul implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(ViewPortAccess.class).annotatedWith(Names.named("ViewPortAccess")).to(ViewPortImpl.class);
		binder.bind(ViewPort.class).annotatedWith(Names.named("ViewPort")).to(ViewPortImpl.class);
		
	}

}
