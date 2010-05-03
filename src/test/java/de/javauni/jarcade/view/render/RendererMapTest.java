package de.javauni.jarcade.view.render;

import com.google.inject.AbstractModule;
import static org.junit.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

import de.javauni.jarcade.impl.OutputModule;
import de.javauni.jarcade.impl.RendererModule;
import de.javauni.jarcade.impl.scene.SceneImpl;
import de.javauni.jarcade.impl.scene.SimpleEntity;

import de.javauni.jarcade.impl.view.RendererMapImpl;
import de.javauni.jarcade.impl.view.renderers.GeneralEntityRenderer;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.utils.geom.Box;

import de.javauni.utils.guice.ScopeManagerModule;
import de.javauni.yarrish.view.RendererBindingsModule;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.junit.Before;
import org.junit.Test;

public class RendererMapTest {

    private Injector inj;

    @Before
    public void setUp() {
        inj = Guice.createInjector(
                new ScopeManagerModule(),
                new RendererModule(),
                new RendererBindingsModule(),
                new OutputModule(),
                new AbstractModule() {

                    @Override
                    protected void configure() {
                        bind(Executor.class).annotatedWith(Names.named("channel-broadcast-executor")).
                                toInstance(Executors.newSingleThreadExecutor());
                        //bind(SimpleEntity.class).toProvider(Providers.of(null));
                        bind(Scene.class).to(SceneImpl.class);
                    }
                });
    }

    @Test
    public void newMapIsEmpty() {
        RendererMap map = inj.getInstance(RendererMapImpl.class);
        Renderer<? super SimpleEntity> entyrenderer =
                map.get(new SimpleEntity(0, null));
        assertTrue("renderer returned from empty map is null", entyrenderer
                == null);
    }

    @Test
    public void putAndGetOne() {
        RendererMap map = inj.getInstance(RendererMapImpl.class);
        SimpleEntity enty = new SimpleEntity(0, new Box(50.0f, 50.0f, 50, 50));
        Renderer<SimpleEntity> entyrend = new GeneralEntityRenderer();
        map.put(enty, entyrend);
        Renderer<? super SimpleEntity> entyren2 = map.get(enty);
        assertTrue("the renderer put into the map is returend", entyrend
                == entyren2);
    }
}
