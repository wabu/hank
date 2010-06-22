package de.javauni.jarcade.presenter.impl;

import java.util.HashMap;
import java.util.Map;

import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.presenter.rendering.Renderer;
import de.javauni.jarcade.presenter.rendering.RendererMap;
import de.javauni.jarcade.utils.guice.ManagedScope;

import de.javauni.jarcade.view.GraphicsContext;

@ManagedScope
public class RendererMapImpl<G extends GraphicsContext> implements RendererMap<G> {
    // XXX make a faster implemntation using entity.getId() and IDList
    private final Map<Entity, Renderer<G,?>> rendererMap
            = new HashMap<Entity, Renderer<G,?>>();

    @Override
    @SuppressWarnings("unchecked")
    public <E> Renderer<G, ? super E> get(Entity entity) {
        return (Renderer<G, ? super E>)rendererMap.get(entity);
    }

    @Override
    public <E> void put(Entity entity, Renderer<G, ? super E> renderer) {
        rendererMap.put(entity, renderer);
    }
}
