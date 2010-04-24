package de.javauni.yarrish.view.renderer;

import static org.junit.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.jarcade.renderer.Thread.RendererThread;
import de.javauni.jarcade.renderer.Thread.RendererThreadImpl;
import de.javauni.jarcade.renderer.Thread.RendererThreadModul;


public class RendererThreadTest {
	@org.junit.Test public void teststopRunning(){
		Injector inj = Guice.createInjector(new RendererThreadModul());
		RendererThread thread = inj.getInstance(RendererThreadImpl.class);
		Thread t = new Thread(thread);
		t.start();
		thread.stopIt();
		Thread.yield();
		assertTrue(!t.isAlive());
	}
	
	@org.junit.Test(expected= IllegalArgumentException.class) public void testSetFpsZero(){
		Injector inj = Guice.createInjector(new RendererThreadModul());
		RendererThread thread = inj.getInstance(RendererThreadImpl.class);
		thread.setFramePerSecond(0);
	}
	
	@org.junit.Test public void testConstruktorFpsZero(){
		Injector inj = Guice.createInjector(new RendererThreadModul(0,null,null,null));
		RendererThread thread = inj.getInstance(RendererThreadImpl.class);
		assertTrue((int)thread.getFramePerSecond()==60);
	}
}