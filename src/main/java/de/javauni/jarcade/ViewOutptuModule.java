package de.javauni.jarcade;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import com.google.inject.name.Names;

import de.javauni.jarcade.model.scene.Scene;

import de.javauni.jarcade.view.GraphicsOutput;
import de.javauni.jarcade.view.RenderedView;

import de.javauni.jarcade.view.impl.JavaGraphicsContext;
import de.javauni.jarcade.view.impl.JavaGraphicsOutput;
import de.javauni.jarcade.view.impl.SceneView;

public class ViewOutptuModule extends AbstractModule {

	@Override
	protected void configure() {
        bind(new TypeLiteral<GraphicsOutput<JavaGraphicsContext>>(){})
            .to(JavaGraphicsOutput.class);
        bind(new TypeLiteral<RenderedView<Scene, JavaGraphicsContext>>(){})
            .to(SceneView.class);

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
