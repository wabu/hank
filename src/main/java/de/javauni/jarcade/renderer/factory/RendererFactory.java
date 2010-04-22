package de.javauni.jarcade.renderer.factory;

import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.renderer.Renderer;

public interface RendererFactory {
	<E extends Entity> Renderer<? super E> getRenderer(E entity);
}
