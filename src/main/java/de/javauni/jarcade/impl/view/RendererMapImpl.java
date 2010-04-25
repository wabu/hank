package de.javauni.jarcade.impl.view;

import de.javauni.jarcade.view.render.RendererMap;
import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.jarcade.view.render.Renderer;
import java.util.HashMap;
import java.util.Map;

public class RendererMapImpl implements RendererMap {
    // XXX make a faster implemntation using entity.getId() and IDList
    private final Map<Entity, Renderer<?>> rendererMap
            = new HashMap<Entity, Renderer<?>>();

    @Override
    @SuppressWarnings("unchecked")
    public <E> Renderer<? super E> get(Entity entity) {
        return (Renderer<? super E>)rendererMap.get(entity);
    }

    @Override
    public <E> void put(Entity entity, Renderer<? super E> renderer) {
        rendererMap.put(entity, renderer);
    }
}
