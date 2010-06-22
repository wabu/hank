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

import java.util.Random;

import com.google.common.base.Function;

import com.google.inject.Inject;
import com.google.inject.Provider;

import de.javauni.jarcade.geom.immutable.BoundI;
import de.javauni.jarcade.geom.immutable.RectI;

import de.javauni.jarcade.model.StateModel;

import de.javauni.jarcade.model.entities.Entity;
import de.javauni.jarcade.model.impl.event.Broadcastor;
import de.javauni.jarcade.model.impl.event.Channel;

import de.javauni.jarcade.model.phys.ControlableBody;
import de.javauni.jarcade.model.phys.SimpleDynamicBody;
import de.javauni.jarcade.model.phys.SimpleStaticBody;

import de.javauni.jarcade.model.scene.SceneModel;

import de.javauni.jarcade.model.scene.event.LayerChangeListener;
import de.javauni.jarcade.model.scene.impl.LayerImpl;
import de.javauni.jarcade.model.scene.impl.SceneModelImpl;
import de.javauni.jarcade.model.scene.impl.operate.SceneUpdateLoop;


import de.javauni.jarcade.utils.guice.ManagedScope;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@ManagedScope
public class LevelModelImpl extends SceneModelImpl implements LevelModel {
    private final LevelScene scene;
    private final Provider<Channel<LayerChangeListener>> layerChan;
    private final Channel<CharacterControlListener> charChan;

    @Inject
    public LevelModelImpl(
            final Channel<StateModel.ChangeListener<SceneModel.Phase>> sceneChan,
            final Channel<CharacterControlListener> charChan,
            final LevelScene scene, final SceneUpdateLoop loop,
            final Provider<Channel<LayerChangeListener>> layerChan) {
        super(sceneChan, scene, loop);
        this.scene = scene;
        this.layerChan = layerChan;
        this.charChan = charChan;
    }

    @Override
    public void loadLevel(String ressources) {
        // XXX layer channel foo
        scene.setBounds(0,-10,200,150);
        // TODO factory
        scene.addLayer(new LayerImpl(0, 0, new BoundI(0, -10, 200, 150),
                    layerChan.get()));
        scene.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer id) {
                return new SimpleStaticBody(id, new RectI(0, -10, 100, 10));
            }
        }, 0);
        scene.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer id) {
                return new SimpleStaticBody(id, new RectI(100, 0, 100, 10));
            }
        }, 0);
        scene.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer id) {
                final ControlableBody c = new ControlableBody(id, new BoundI(20, 0, 3, 5));
                charChan.broadcast(new Broadcastor<CharacterControlListener>() {
                    @Override
                    public void apply(CharacterControlListener l) {
                        l.newCharacterControlCreated(c, 0);
                    }
                });
                return c;
            }
        }, 0);
        final Random rnd = new Random();
        for (int i=0; i<100; i++) {
            scene.addEntity(new Function<Integer, Entity>() {
                public Entity apply(Integer id) {
                    return new SimpleDynamicBody(id, new RectI(
                            100+(float)rnd.nextGaussian()*10, 
                            100+(float)rnd.nextGaussian()*30, 3, 3));
                }
            }, 0);
        }
        // TODO behavior, perhaps in super classes
    }

    public LevelScene getScene() {
        return scene;
    }

    @Override
    public Channel<CharacterControlListener> getCharacterChannel() {
        return charChan;
    }
}
