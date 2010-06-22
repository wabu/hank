package de.javauni.jarcade;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import com.google.inject.name.Names;

import de.javauni.jarcade.presenter.impl.GuicyRendererFactory;
import de.javauni.jarcade.presenter.impl.RendererMapImpl;
import de.javauni.jarcade.presenter.rendering.RendererFactory;
import de.javauni.jarcade.presenter.rendering.RendererMap;

import de.javauni.jarcade.view.impl.JavaGraphicsContext;

public class PreseterRenderingModule extends AbstractModule {
    @Override
    public void configure() {
        bind(new TypeLiteral<RendererFactory<JavaGraphicsContext>>(){})
            .to(new TypeLiteral<GuicyRendererFactory<JavaGraphicsContext>>(){});
        bind(new TypeLiteral<RendererMap<JavaGraphicsContext>>(){})
            .to(new TypeLiteral<RendererMapImpl<JavaGraphicsContext>>(){});

        bind(Long.class).annotatedWith(Names.named("rendering-update-intervall"))
            .toInstance(25L);
        bind(ThreadFactory.class).annotatedWith(Names.named("rendering-thread-factory"))
            .toInstance(Executors.defaultThreadFactory());
    }
}
