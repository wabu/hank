package main;
import java.awt.Color;

import output.Output;
import output.OutputImpl;
import output.OutputModule;
import renderer.Entity.Box;
import renderer.Entity.SimpleEntity;
import renderer.rendererFactory.RendererFactory;
import renderer.rendererFactory.RendererFactoryImpl;
import renderer.rendererFactory.RendererFactoryModul;
import renderer.rendererMap.RendererMap;
import renderer.rendererMap.RendererMapImpl;
import renderer.rendererMap.RendererMapModule;
import renderer.rendererThread.RendererThread;
import renderer.rendererThread.RendererThreadImpl;
import renderer.rendererThread.RendererThreadModul;

import ViewPort.ViewPortAccess;
import ViewPort.ViewPortImpl;
import ViewPort.ViewPortModul;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		SimpleEntity enty 				= new SimpleEntity(1, new Box(5f,5f,50,50));
		Injector inj 					= Guice.createInjector(new OutputModule(),new RendererMapModule(), new RendererFactoryModul(), new ViewPortModul());
		Output out 						= inj.getInstance(OutputImpl.class);
		RendererMap map 				= inj.getInstance(RendererMapImpl.class);
		RendererFactory factory 		= inj.getInstance(RendererFactoryImpl.class);
		ViewPortAccess viewPortAccess 	= inj.getInstance(ViewPortImpl.class);
		Injector inj2					= Guice.createInjector(new RendererThreadModul(60,map,out,viewPortAccess));
		RendererThread thread 			= inj2.getInstance(RendererThreadImpl.class);
		map.put(enty, factory.getRenderer(enty));
		viewPortAccess.entityEntersViewport(enty);
		new Thread(thread).start();
		Thread.sleep((long) 2E3);
		out.getGhostGraphics().setColor(Color.red);
		out.getGhostGraphics().fillRect(0, 0, 800, 600);
		out.repaint();
		
		
		
		
	}
}
