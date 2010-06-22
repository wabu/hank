package de.javauni.yarrish;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import de.javauni.jarcade.model.phys.ControlableBody;
import de.javauni.jarcade.model.phys.SimpleDynamicBody;
import de.javauni.jarcade.model.phys.SimpleStaticBody;
import de.javauni.jarcade.presenter.rendering.Renderer;

import de.javauni.jarcade.view.impl.JavaGraphicsContext;


import de.javauni.jarcade.view.render.GeneralEntityRenderer;

public class EntityModule extends AbstractModule {

	@Override
	protected void configure() {
        bind(new TypeLiteral<Renderer<JavaGraphicsContext, ? super SimpleStaticBody>>(){})
            .to(new TypeLiteral<GeneralEntityRenderer>(){});
        bind(new TypeLiteral<Renderer<JavaGraphicsContext, ? super SimpleDynamicBody>>(){})
            .to(new TypeLiteral<GeneralEntityRenderer>(){});
        bind(new TypeLiteral<Renderer<JavaGraphicsContext, ? super ControlableBody>>(){})
            .to(new TypeLiteral<GeneralEntityRenderer>(){});
	}
}

