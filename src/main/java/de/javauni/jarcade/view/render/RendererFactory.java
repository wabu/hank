package de.javauni.jarcade.view.render;

import de.javauni.jarcade.model.entities.Entity;

public interface RendererFactory {
	<E extends Entity> Renderer<? super E> getRenderer(E entity);
    boolean isRenderable(Entity entity);
}
