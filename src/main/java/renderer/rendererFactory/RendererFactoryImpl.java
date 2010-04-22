package renderer.rendererFactory;

import javax.swing.JComboBox.KeySelectionManager;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import renderer.Renderer;
import renderer.Entity.Entity;
import renderer.Entity.EntityModule;

public class RendererFactoryImpl implements RendererFactory
{
	@Override
	public <E extends Entity> Renderer<? super E> getRenderer(E entity) {
		Injector inj3 = Guice.createInjector(new EntityModule(entity));
		return inj3.getInstance(Renderer.class);
		
	}
}
