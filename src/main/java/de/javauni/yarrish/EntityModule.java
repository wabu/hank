package de.javauni.yarrish;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import de.javauni.jarcade.model.phys.Grounded;

import de.javauni.jarcade.view.render.Renderer;

import de.javauni.jarcade.view.renderers.GeneralEntityRenderer;

public class EntityModule extends AbstractModule {

	@Override
	protected void configure() {
        bind(new TypeLiteral<Renderer<? super Grounded>>(){})
            .to(new TypeLiteral<GeneralEntityRenderer>(){});
	}

}
