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
import de.javauni.jarcade.event.Channel;

import de.javauni.jarcade.impl.phys.Block;
import de.javauni.jarcade.impl.phys.Ground;
import de.javauni.jarcade.impl.scene.LayerImpl;
import de.javauni.jarcade.impl.scene.SceneModelImpl;
import de.javauni.jarcade.impl.scene.SceneUpdateLoop;
import de.javauni.jarcade.model.StateListener;
import de.javauni.jarcade.model.scene.LayerChangeListener;
import de.javauni.jarcade.model.scene.ScenePhase;
import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.utils.geom.Box;
import de.javauni.utils.guice.ManagedScope;

import java.io.IOException;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@ManagedScope
public class LevelModelImpl extends SceneModelImpl implements LevelAccess, LevelExport {
    private final LevelScene space;
    private final Provider<Channel<LayerChangeListener>> layerChan;

    @Inject
    public LevelModelImpl(
            final Channel<StateListener<ScenePhase>> chan, final LevelScene scene, final SceneUpdateLoop loop,
            final Provider<Channel<LayerChangeListener>> layerChan) {
        super(chan, scene, loop);
        this.space = scene;
        this.layerChan = layerChan;
    }

    @Override
    public void loadLevel(String ressources) throws IOException {
        // XXX layer channel foo
        space.getWorldBox().setBox(0,-1, 100, 51);
        space.addLayer(new LayerImpl(0, 0, layerChan.get()));

        space.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer f) {
                return new Ground(f, new Box(0,-10, 100, 10), new Box(0,-10, 100, 10));
            }
        }, 0);
        space.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer f) {
                return new Block(f, new Box(1, 1, .5f, .5f), new Box(1, 1, .5f, .5f), 3f);
            }
        }, 0);

        space.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer f) {
                return new Block(f, new Box(1.5f, 1.6f, .5f, .5f), new Box(1, 1, .5f, .5f), 3f);
            }
        }, 0);
        // TODO behavior, perhaps in super classes
    }

    public LevelScene getScene() {
        return space;
    }
}
