package de.javauni.jarcade.impl;

import de.javauni.jarcade.view.render.Renderer;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import de.javauni.jarcade.impl.scene.SimpleEntity;
import de.javauni.jarcade.impl.view.renderers.SimpleEntityRenderer;

public class DefaultRendererBindingsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<Renderer<? extends SimpleEntity>>(){})
                .to(SimpleEntityRenderer.class);
    };
}
