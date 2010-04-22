package de.javauni.jarcade.renderer.factory;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class RendererFactoryModul implements Module{

	@Override
	public void configure(Binder binder) {
		binder.bind(RendererFactory.class).annotatedWith(Names.named("RendererFactory")).to(RendererFactoryImpl.class);
	}

}
