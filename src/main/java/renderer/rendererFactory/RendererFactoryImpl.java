package renderer.rendererFactory;

import renderer.Renderer;
import renderer.Entity.Entity;
import renderer.Entity.SimpleEntity;
import renderer.Entity.SimpleEntityRenderer;

public class RendererFactoryImpl implements RendererFactory
{
//	@Override
//	public <E> Renderer<E> getRenderer(Entity E) {
//		switch(E.getId()){
//			case 1: {
//				Renderer<SimpleEntity> rend = new SimpleEntityRenderer();
//				return rend;
//			}
//		}
//		return null;
//	}

	@Override
	public <E extends Entity> Renderer<? super E> getRenderer(E entity) {
		
		if(entity instanceof SimpleEntity) {
			return (Renderer<? super E>) new SimpleEntityRenderer();
		}

		return null;
	}
}
