package de.javauni.jarcade.view.render;

import static org.junit.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.jarcade.impl.space.SimpleEntity;

import de.javauni.jarcade.view.render.RendererMap;
import de.javauni.jarcade.view.impl.RendererMapImpl;
import de.javauni.jarcade.view.impl.RendererModule;
import de.javauni.jarcade.view.render.Renderer;
import de.javauni.jarcade.view.SimpleEntityRenderer;
import de.javauni.utils.geom.Box;
import org.junit.Test;

public class RendererMapTest {
    @Test
    public void newMapIsEmpty() {
        Injector inj = Guice.createInjector(new RendererModule());
        RendererMap map = inj.getInstance(RendererMapImpl.class);
        Renderer<? super SimpleEntity> entyrenderer = map.get(new SimpleEntity(0, null));
        assertTrue("renderer returned from empty map is null", entyrenderer == null);
    }

    @Test
    public void putAndGetOne() {
        Injector inj = Guice.createInjector(new RendererModule());
        RendererMap map = inj.getInstance(RendererMapImpl.class);
        SimpleEntity enty = new SimpleEntity(0, new Box(50.0f, 50.0f, 50, 50));
        Renderer<SimpleEntity> entyrend = new SimpleEntityRenderer();
        map.put(enty, entyrend);
        Renderer<? super SimpleEntity> entyren2 = map.get(enty);
        assertTrue("the renderer put into the map is returend", entyrend == entyren2);
    }
}
