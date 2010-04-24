package de.javauni.jarcade.view.render;

import com.google.inject.AbstractModule;
import static org.junit.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import de.javauni.jarcade.impl.space.SpaceImpl;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.view.RendererBindingsModule;
import de.javauni.jarcade.view.impl.RendererModule;
import de.javauni.jarcade.view.impl.RendererThreadImpl;
import de.javauni.jarcade.view.output.OutputModule;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.junit.Before;
import org.junit.Test;

public class RendererThreadTest {

    private Injector inj;

    @Before
    public void setUp() {
        inj = Guice.createInjector(
                new RendererModule(),
                new RendererBindingsModule(),
                new OutputModule(),
                new AbstractModule() {

                    @Override
                    protected void configure() {
                        bind(Executor.class).annotatedWith(Names.named("channel-broadcast-executor")).
                                toInstance(Executors.newSingleThreadExecutor());
                        //bind(SimpleEntity.class).toProvider(Providers.of(null));
                        bind(Scene.class).to(SpaceImpl.class);
                    }
                });
    }

    @Test
    public void teststopRunning() {
        RendererThread thread = inj.getInstance(RendererThreadImpl.class);
        Thread t = new Thread(thread);
        t.start();
        thread.stopIt();
        Thread.yield();
        assertFalse("stop thread is not alive", t.isAlive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFpsZero() {
        RendererThread thread = inj.getInstance(RendererThreadImpl.class);
        thread.setFramePerSecond(0);
    }

    @Test
    public void testConstruktorFpsZero() {
        RendererThread thread = inj.getInstance(RendererThreadImpl.class);
        assertTrue("fps are positive", (int) thread.getFramePerSecond() >= 0);
    }
}
