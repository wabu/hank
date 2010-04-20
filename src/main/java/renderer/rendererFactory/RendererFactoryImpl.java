package renderer.rendererFactory;

import renderer.Entity.Entity;
import renderer.Entity.SimpleEntity;
import renderer.Entity.SimpleEntityRenderer;
import renderer.Renderer;

public class RendererFactoryImpl implements RendererFactory
{
	@Override
    @SuppressWarnings("unchecked")
	public <E extends Entity> Renderer<? super E> getRenderer(E entity) {
		if(entity instanceof SimpleEntity) {
            // TODO use guice
			return (Renderer<? super E>)(Renderer<?>)(new SimpleEntityRenderer());
		}
		return null;
	}
}
