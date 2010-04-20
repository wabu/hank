package renderer.rendererMap;

import java.util.HashMap;
import java.util.Map;

import renderer.Renderer;
import renderer.Entity.Entity;
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
