package renderer.rendererMap;

import renderer.Renderer;
import renderer.Entity.Entity;
public interface RendererMap {
	<E> void put(Entity Entity, Renderer<E> renderer);
	<E> Renderer<E> get(Entity Entity);
}
