package de.javauni.jarcade.renderer.map;

import de.javauni.jarcade.model.space.Entity;
import java.util.HashMap;
import java.util.Map;

import de.javauni.jarcade.renderer.Renderer;
public class RendererMapImpl implements RendererMap{
	
	Map<Entity, Renderer> rendererMap = new HashMap<Entity, Renderer>();
	@Override
	public <E> Renderer<E> get(Entity entity) {
		return rendererMap.get(entity);
	}

	@Override
	public <E> void put(Entity entity, Renderer<E> renderer) {
		rendererMap.put(entity, renderer);
	}

}
