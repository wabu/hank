package renderer.rendererFactory;

import output.OutputModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

import renderer.Renderer;
import renderer.Entity.Box;
import renderer.Entity.SimpleEntity;
import renderer.rendererMap.RendererMap;
import renderer.rendererMap.RendererMapImpl;
import renderer.rendererMap.RendererMapModule;

public class Test {
	public static void main(String[] args) {
		Injector inj = Guice.createInjector(new OutputModule(), new RendererFactoryModul(), new RendererMapModule());
		RendererFactory fact = new RendererFactoryImpl();
		RendererMap map = new RendererMapImpl();
		SimpleEntity enty = new SimpleEntity(0, new Box(5f,5f,9,29));
		Renderer<? super SimpleEntity> entyRend = fact.getRenderer(enty);
		map.put(enty,entyRend);
	}
}
