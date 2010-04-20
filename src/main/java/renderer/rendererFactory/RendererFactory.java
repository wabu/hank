package renderer.rendererFactory;

import renderer.Renderer;
import renderer.Entity.Entity;

public interface RendererFactory {
	<E extends Entity> Renderer<? super E> getRenderer(E entity);
}
