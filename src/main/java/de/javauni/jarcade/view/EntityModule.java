package de.javauni.jarcade.view;


import de.javauni.jarcade.renderer.Renderer;

import com.google.inject.Binder;
import com.google.inject.Module;
import de.javauni.jarcade.model.space.Entity;

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
