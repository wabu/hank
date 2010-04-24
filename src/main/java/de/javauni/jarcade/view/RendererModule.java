package de.javauni.jarcade.view;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import de.javauni.jarcade.impl.space.SimpleEntity;

public class RendererModule extends AbstractModule {
    @Override
    protected void configure() {
        Class<? extends Renderer<SimpleEntity>> c = null;//SimpleEntityRenderer.class;
        bind(new TypeLiteral<Renderer<? extends SimpleEntity>>(){})
                .to(c);
    };
}
