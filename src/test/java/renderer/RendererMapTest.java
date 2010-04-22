package renderer;

import static org.junit.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;

import renderer.Renderer;
import renderer.Entity.*;
import renderer.rendererMap.RendererMap;
import renderer.rendererMap.RendererMapImpl;
import renderer.rendererMap.RendererMapModule;

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
