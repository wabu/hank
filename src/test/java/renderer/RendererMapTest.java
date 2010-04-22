package de.javauni.yarrish.view.renderer;

import static org.junit.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.jarcade.impl.space.SimpleEntity;

import de.javauni.jarcade.renderer.Renderer;
import de.javauni.jarcade.renderer.map.RendererMap;
import de.javauni.jarcade.renderer.map.RendererMapImpl;
import de.javauni.jarcade.renderer.map.RendererMapModule;
import de.javauni.jarcade.view.SimpleEntityRenderer;
import de.javauni.utils.geom.Box;

public class RendererMapTest {
	@org.junit.Test public void newMapIsEmpty(){
		Injector inj = Guice.createInjector(new RendererMapModule());
		RendererMap map = inj.getInstance(RendererMapImpl.class);
		Renderer<SimpleEntityRenderer> entyrenderer = map.get(null);
		assertTrue(entyrenderer==null);		
	}
	
	@org.junit.Test public void putAndGetOne(){
		Injector inj = Guice.createInjector(new RendererMapModule());
		RendererMap map = inj.getInstance(RendererMapImpl.class);
		SimpleEntity enty = new SimpleEntity(0, new Box(50.0f,50.0f,50,50));
		Renderer<SimpleEntity> entyrend = new SimpleEntityRenderer();
		map.put(enty,entyrend);
		Renderer<SimpleEntity> entyren2=map.get(enty);
		assertTrue(entyrend==entyren2);
	}
}
