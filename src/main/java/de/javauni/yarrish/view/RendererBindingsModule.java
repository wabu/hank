package de.javauni.yarrish.view;

import de.javauni.jarcade.impl.phys.Block;
import de.javauni.jarcade.impl.phys.Ground;

import de.javauni.jarcade.view.render.Renderer;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import de.javauni.jarcade.impl.view.renderers.SimpleEntityRenderer;

public class RendererBindingsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<Renderer<? super Block>>(){})
                .to(SimpleEntityRenderer.class);
        bind(new TypeLiteral<Renderer<? super Ground>>(){})
                .to(SimpleEntityRenderer.class);
    };
}
