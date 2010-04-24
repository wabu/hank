package de.javauni.jarcade.view;

import de.javauni.jarcade.view.render.Renderer;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import de.javauni.jarcade.impl.space.SimpleEntity;

public class RendererBindingsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<Renderer<? extends SimpleEntity>>(){})
                .to(SimpleEntityRenderer.class);
    };
}
