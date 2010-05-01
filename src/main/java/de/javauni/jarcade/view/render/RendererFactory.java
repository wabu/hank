package de.javauni.jarcade.view.render;

import de.javauni.jarcade.model.scene.entity.Entity;

public interface RendererFactory {
	<E extends Entity> Renderer<? super E> getRenderer(E entity);
    boolean isRenderable(Entity entity);
}
