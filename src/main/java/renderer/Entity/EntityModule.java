package renderer.Entity;

import java.io.File;

import renderer.Renderer;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class EntityModule implements Module {
	String nameOfEntity;
	Entity entity;
	public EntityModule(Entity entity) {
		this.entity=entity;
		
		nameOfEntity = entity.getClass().getCanonicalName() + "Renderer";
	}

	@Override
	public void configure(Binder binder) {
		try {
			binder.bind(Renderer.class).to((Class<? extends Renderer>) ClassLoader.getSystemClassLoader().loadClass(nameOfEntity));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
