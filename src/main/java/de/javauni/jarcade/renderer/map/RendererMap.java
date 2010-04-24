package de.javauni.jarcade.renderer.map;

import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.view.Renderer;

public interface RendererMap {
	<E> void put(Entity Entity, Renderer<E> renderer);
	<E> Renderer<E> get(Entity Entity);
}
