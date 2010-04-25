package de.javauni.jarcade.impl;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

import de.javauni.jarcade.impl.view.gui.OutputImpl;
import de.javauni.jarcade.view.render.Output;

public class OutputModule implements Module{
	@Override
	public void configure(Binder binder) {
		binder.bind(Output.class).to(OutputImpl.class);

		binder.bind(String.class).annotatedWith(Names.named("win-name"))
            .toInstance("JArcade");
		binder.bind(Integer.class).annotatedWith(Names.named("win-x"))
            .toInstance(0);
		binder.bind(Integer.class).annotatedWith(Names.named("win-y"))
            .toInstance(0);
		binder.bind(Integer.class).annotatedWith(Names.named("win-height"))
            .toInstance(600);
		binder.bind(Integer.class).annotatedWith(Names.named("win-width"))
            .toInstance(800);

	}
	
}
