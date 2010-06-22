package de.javauni.jarcade;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.inject.AbstractModule;

import com.google.inject.name.Names;

import de.javauni.jarcade.presenter.impl.GuicyRendererFactory;
import de.javauni.jarcade.presenter.impl.RendererMapImpl;
import de.javauni.jarcade.presenter.rendering.RendererFactory;
import de.javauni.jarcade.presenter.rendering.RendererMap;

public class ViewRenderModule extends AbstractModule {
    @Override
    public void configure() {
        bind(RendererFactory.class).to(GuicyRendererFactory.class);
        bind(RendererMap.class).to(RendererMapImpl.class);

        bind(Long.class).annotatedWith(Names.named("render-intervall"))
            .toInstance(25L);
        bind(ThreadFactory.class).annotatedWith(Names.named("render-thread"))
            .toInstance(Executors.defaultThreadFactory());
    }
}
