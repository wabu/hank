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
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package de.javauni.yarrish.model.level;

import com.google.common.base.Function;

import com.google.inject.Inject;
import com.google.inject.Provider;

import java.io.IOException;

import de.javauni.jarcade.geom.immutable.BoundI;
import de.javauni.jarcade.geom.immutable.RectI;

import de.javauni.jarcade.model.StateListener;

import de.javauni.jarcade.model.event.Channel;

import de.javauni.jarcade.model.phys.SimpleDynamicBody;
import de.javauni.jarcade.model.phys.SimpleStaticBody;

import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.LayerImpl;
import de.javauni.jarcade.model.scene.SceneModelImpl;
import de.javauni.jarcade.model.scene.ScenePhase;

import de.javauni.jarcade.model.scene.event.LayerChangeListener;

import de.javauni.jarcade.model.scene.operate.SceneUpdateLoop;

import de.javauni.jarcade.utils.guice.ManagedScope;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@ManagedScope
public class LevelModelImpl extends SceneModelImpl implements LevelAccess, LevelExport {
    private final LevelScene scene;
    private final Provider<Channel<LayerChangeListener>> layerChan;

    @Inject
    public LevelModelImpl(
            final Channel<StateListener<ScenePhase>> chan, 
            final LevelScene scene, final SceneUpdateLoop loop,
            final Provider<Channel<LayerChangeListener>> layerChan) {
        super(chan, scene, loop);
        this.scene = scene;
        this.layerChan = layerChan;
    }

    @Override
    public void loadLevel(String ressources) throws IOException {
        // XXX layer channel foo
        scene.setBounds(0,-10,200,100);
        // TODO factory
        scene.addLayer(new LayerImpl(0, 0, new BoundI(0, -10, 200, 100),
                    layerChan.get()));

        scene.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer id) {
                return new SimpleStaticBody(id, new RectI(0, -10, 200, 10));
            }
        }, 0);
        scene.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer id) {
                return new SimpleDynamicBody(id, new RectI(21, 13, 5, 5));
            }
        }, 0);
        scene.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer id) {
                return new SimpleDynamicBody(id, new RectI(23, 20, 5, 5));
            }
        }, 0);

        // TODO behavior, perhaps in super classes
    }

    public LevelScene getScene() {
        return scene;
    }
}
