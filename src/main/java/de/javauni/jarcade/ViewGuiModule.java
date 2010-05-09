package de.javauni.jarcade;

import com.google.inject.AbstractModule;

import com.google.inject.name.Names;

import de.javauni.jarcade.view.gui.Output;
import de.javauni.jarcade.view.gui.OutputFrame;

public class ViewGuiModule extends AbstractModule {

	@Override
	protected void configure() {
        bind(Output.class).to(OutputFrame.class);
        bind(Integer.class).annotatedWith(Names.named("win-x"))
            .toInstance(0);
        bind(Integer.class).annotatedWith(Names.named("win-y"))
            .toInstance(0);
        bind(Integer.class).annotatedWith(Names.named("win-width"))
            .toInstance(800);
        bind(Integer.class).annotatedWith(Names.named("win-height"))
            .toInstance(600);
        bind(String.class).annotatedWith(Names.named("win-name"))
            .toInstance("jarcade");
	}
}
