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

package de.javauni.jarcade.model.scene.impl;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import com.google.inject.Inject;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.annotation.Nullable;

import de.javauni.jarcade.geom.Bound;

import de.javauni.jarcade.geom.immutable.BoundI;

import de.javauni.jarcade.model.impl.event.Broadcastor;
import de.javauni.jarcade.model.impl.event.Channel;

import de.javauni.jarcade.model.scene.Layer;

import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.Viewport;
import de.javauni.jarcade.model.scene.event.SceneChangeListener;
import de.javauni.jarcade.model.scene.event.ViewportListener;

import de.javauni.jarcade.utils.IdList;

import de.javauni.jarcade.utils.guice.ManagedScope;

/**
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@ManagedScope
public class SceneImpl implements Scene, Scene.Edit {
    private final IdList<Entity> entities;
    private final SortedMap<Integer, Layer.Edit> layers;
    // XXX when to get t3h zero layer
    @Nullable private Layer.Edit zero;
    @Nullable private Bound size;
    private final Channel<SceneChangeListener> chan;

    private final Viewport view;

    @Inject
    public SceneImpl(
            Channel<SceneChangeListener> chan,
            Channel<ViewportListener> viewChan) {
        this.chan = chan;
        this.entities = new IdList<Entity>();
        this.layers = new TreeMap<Integer, Layer.Edit>();
        this.view = new WholeSceneView(viewChan, this);

        this.zero = null;
    }

    @Override
    public Bound getWorldSize() {
        return size;
    }

    public void setBounds(float x, float y, float w, float h) {
        this.size = new BoundI(x, y, w, h);
    }

    @Override
    public Channel<SceneChangeListener> getSceneChannel() {
        return chan;
    }

    @Override
    public Layer.Edit getLayer(int index) {
        if(!layers.containsKey(index)){
            throw new IndexOutOfBoundsException(
                    "no layer with index "+index+" in this space");
        }
        return layers.get(index);
    }

    @Override
    public Iterable<? extends Layer.Edit> getLayers() {
        return layers.values();
    }

    @Override
    public Layer.Edit getZeroLayer() {
        return zero;
    }

    @Override
    public Entity getEntity(int id) {
        return entities.get(id);
    }

    @Override
    public Iterable<Entity> getAllEntities() {
        return Iterables.filter(entities, Predicates.notNull());
    }

    @Override
    public int addLayer(final Layer.Edit lay) throws IllegalArgumentException {
        if(layers.containsKey(lay.getIndex())) {
            throw new IllegalArgumentException(
                    "space already contains a layer with index "+lay.getIndex());
        }
        if(lay.getIndex() == 0) {
            zero = lay;
        }
        layers.put(lay.getIndex(), lay);

        chan.broadcast(new Broadcastor<SceneChangeListener>() {
            @Override
            public void apply(SceneChangeListener l) {
                l.onLayerAdded(lay);
            }
                });

        return lay.getIndex();
    }

    @Override
    public <E extends Entity> E addEntity(Function<Integer, E> construct, final int layerIndex)
            throws IndexOutOfBoundsException {
        final Layer.Edit layer = getLayer(layerIndex);
        final E entity = entities.add(construct);
        layer.add(entity);

        chan.broadcast(new Broadcastor<SceneChangeListener>() {
            @Override
            public void apply(SceneChangeListener l) {
                l.onEntitySpawned(entity, layer);
            }
        });
        return entity;
    }

    @Override
    public void moveEntity(final Entity e, int oldLayer, int newLayer) 
            throws NoSuchElementException, IndexOutOfBoundsException {
        final Layer.Edit l1 = getLayer(oldLayer);
        final Layer.Edit l2 = getLayer(newLayer);

        l1.remove(e);
        l2.add(e);

        chan.broadcast(new Broadcastor<SceneChangeListener>() {
            @Override
            public void apply(SceneChangeListener l) {
                l.onEntityLayerChange(e, l1, l2);
            }
        });
    }

    @Override
    public void removeEnity(final Entity e, int layerIndex) 
            throws NoSuchElementException, IndexOutOfBoundsException {
        final Layer.Edit layer = getLayer(layerIndex);
        layer.remove(e);
        entities.remove(e);

        chan.broadcast(new Broadcastor<SceneChangeListener>() {
            @Override
            public void apply(SceneChangeListener l) {
                l.onEntitySpawned(e, layer);
            }
        });
    }

    @Override
    public Viewport getViewport() {
        return view;
    }
}
