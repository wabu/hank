package de.javauni.jarcade.presenter.impl;

import com.google.inject.Inject;

import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.presenter.rendering.Renderer;
import de.javauni.jarcade.presenter.rendering.RendererFactory;

import com.google.inject.Injector;
import com.google.inject.Key;

import com.google.inject.util.Types;

import de.javauni.jarcade.utils.guice.ManagedScope;

import de.javauni.jarcade.view.GraphicsContext;

import de.javauni.jarcade.view.impl.JavaGraphicsContext;

@ManagedScope
public class GuicyRendererFactory<G extends GraphicsContext> implements RendererFactory<G> {
    private Injector inj;

    @Inject
    void initialize(Injector inj) {
        this.inj = inj;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends Entity> Renderer<G, ? super E> getRenderer(E entity) {
        Class<?> klass = entity.getClass();
        // FIXME JavaGraphicsContext should be the type parameter not the impl
        return (Renderer<G, ? super E>)inj.getInstance(
                Key.get(Types.newParameterizedType(Renderer.class, JavaGraphicsContext.class, Types.supertypeOf(klass))));
    }

    @Override
    public boolean isRenderable(Entity entity) {
        // FIXME check bindings
        return true;
    }
}
