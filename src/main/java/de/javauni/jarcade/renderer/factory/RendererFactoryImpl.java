package de.javauni.jarcade.renderer.factory;


import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.renderer.Renderer;
import de.javauni.jarcade.view.EntityModule;

public class RendererFactoryImpl implements RendererFactory
{
	@Override
	public <E extends Entity> Renderer<? super E> getRenderer(E entity) {
		Injector inj3 = Guice.createInjector(new EntityModule(entity));
		return inj3.getInstance(Renderer.class);
		
	}
}
