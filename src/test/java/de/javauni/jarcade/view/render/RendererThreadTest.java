package de.javauni.jarcade.view.render;

import static org.junit.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.jarcade.view.impl.RendererModule;
import de.javauni.jarcade.view.impl.RendererThreadImpl;
import org.junit.Test;


public class RendererThreadTest {
        Injector inj;

        @Test public void teststopRunning(){
                RendererThread thread = inj.getInstance(RendererThreadImpl.class);
                Thread t = new Thread(thread);
                t.start();
                thread.stopIt();
                Thread.yield();
                assertFalse("stop thread is not alive", t.isAlive());
        }
        
        @Test(expected= IllegalArgumentException.class) public void testSetFpsZero(){
                Injector inj = Guice.createInjector(new RendererModule());
                RendererThread thread = inj.getInstance(RendererThreadImpl.class);
                thread.setFramePerSecond(0);
        }
        
        @Test public void testConstruktorFpsZero(){
                Injector inj = Guice.createInjector(new RendererModule());
                RendererThread thread = inj.getInstance(RendererThreadImpl.class);
                assertTrue("fps are positive", (int)thread.getFramePerSecond()>=0);
        }
}
