package de.javauni.jarcade.view.render;

import javax.annotation.Nullable;

import de.javauni.jarcade.model.entities.Entity;

/**
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public interface RendererMap {
    <E> void put(Entity Entity, Renderer<? super E> renderer);
    @Nullable <E> Renderer<? super E> get(Entity Entity);
}
