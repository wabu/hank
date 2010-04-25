package de.javauni.jarcade.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.inject.AbstractModule;

import de.javauni.jarcade.impl.view.RendererFactoryImpl;
import de.javauni.jarcade.impl.view.RendererMapImpl;
import de.javauni.jarcade.impl.view.RendererThreadImpl;
import de.javauni.jarcade.view.render.RendererMap;
import com.google.inject.name.Names;
import de.javauni.jarcade.view.render.RendererFactory;
import de.javauni.jarcade.view.render.RendererThread;

public class RendererModule extends AbstractModule {
    @Override
    public void configure() {
        bind(RendererMap.class).to(RendererMapImpl.class);
        bind(RendererThread.class).to(RendererThreadImpl.class);
        bind(RendererFactory.class).to(RendererFactoryImpl.class);

        bind(Long.class).annotatedWith(Names.named("fps")).toInstance(60L);
        bind(ThreadFactory.class).toInstance(Executors.defaultThreadFactory());
    }
}
