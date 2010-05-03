/*
 *  Copyright (C) 2010 Daniel Waeber <wabu@inf.fu-berlin.de>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.javauni.jarcade.view.render;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import de.javauni.jarcade.impl.OutputModule;
import de.javauni.jarcade.impl.RendererModule;
import de.javauni.jarcade.impl.scene.SceneImpl;
import de.javauni.jarcade.impl.scene.SimpleEntity;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.entity.Entity;

import de.javauni.utils.guice.ScopeManagerModule;
import de.javauni.yarrish.view.RendererBindingsModule;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class RendererFactoryTest {
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
                bind(Executor.class)
                        .annotatedWith(Names.named("channel-broadcast-executor"))
                        .toInstance(Executors.newSingleThreadExecutor());
                //bind(SimpleEntity.class).toProvider(Providers.of(null));
                bind(Scene.class).to(SceneImpl.class);
            }
        }
        );
    }

    @Test(expected=NullPointerException.class)
    public void testGetSimpleRenderer() {
        RendererFactory rf = inj.getInstance(RendererFactory.class);
        Entity e = new SimpleEntity(1, null);

        Renderer<? super Entity> r = rf.getRenderer(e);
        assertNotNull("renderer for simple entity is not null", r);

        r.render(e, null, 0, 0);
    }
}
