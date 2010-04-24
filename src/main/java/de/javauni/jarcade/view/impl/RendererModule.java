package de.javauni.jarcade.view.impl;

import de.javauni.jarcade.view.render.RendererMap;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import de.javauni.jarcade.view.render.RendererFactory;
import de.javauni.jarcade.view.render.RendererThread;

public class RendererModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(RendererMap.class).to(RendererMapImpl.class);
        binder.bind(RendererThread.class).to(RendererThreadImpl.class);
        binder.bind(RendererFactory.class).to(RendererFactoryImpl.class);

        binder.bind(Long.class).annotatedWith(Names.named("fps")).toInstance(60L);
    }
}
