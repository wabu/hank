package de.javauni.jarcade.renderer.factory;

import java.awt.Color;

import de.javauni.jarcade.view.output.Output;
import de.javauni.jarcade.view.output.OutputImpl;
import de.javauni.jarcade.view.output.OutputModule;

import de.javauni.yarrish.model.space.viewport.ViewPortAccess;
import de.javauni.yarrish.model.space.viewport.ViewPortImpl;
import de.javauni.yarrish.model.space.viewport.ViewPortModul;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.jarcade.impl.space.SimpleEntity;

import de.javauni.jarcade.renderer.Renderer;
import de.javauni.jarcade.renderer.map.RendererMap;
import de.javauni.jarcade.renderer.map.RendererMapImpl;
import de.javauni.jarcade.renderer.map.RendererMapModule;
import de.javauni.jarcade.renderer.Thread.RendererThread;
import de.javauni.jarcade.renderer.Thread.RendererThreadImpl;
import de.javauni.jarcade.renderer.Thread.RendererThreadModul;
import de.javauni.utils.geom.Box;

public class Test {
	public static void main(String[] args) {
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
		try {
			Thread.sleep((long) 2E3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.getGhostGraphics().setColor(Color.red);
		out.getGhostGraphics().fillRect(0, 0, 800, 600);
		out.repaint();
	}
}
