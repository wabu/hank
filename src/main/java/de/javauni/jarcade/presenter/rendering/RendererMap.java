package de.javauni.jarcade.presenter.rendering;

import javax.annotation.Nullable;

import de.javauni.jarcade.model.scene.Entity;

import de.javauni.jarcade.view.GraphicsContext;

/**
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public interface RendererMap<G extends GraphicsContext> {
    <E> void put(Entity Entity, Renderer<G, ? super E> renderer);
    @Nullable <E> Renderer<G, ? super E> get(Entity Entity);
}
