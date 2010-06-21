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

import de.javauni.jarcade.geom.Bound;

import de.javauni.jarcade.model.impl.event.Broadcastor;
import de.javauni.jarcade.model.impl.event.Channel;

import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.Layer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import de.javauni.jarcade.model.scene.event.LayerChangeListener;

/**
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class LayerImpl implements Layer.Edit {
    private final Channel<LayerChangeListener> chan;
    private final int index;
    private final int distance;
    private final Bound bound;

    private final List<Entity> entities
            = new ArrayList<Entity>();

    public LayerImpl(int index, int distance, Bound bound,
            Channel<LayerChangeListener> chan) {
        this.index = index;
        this.distance = distance;
        this.chan = chan;
        this.bound = bound;
    }

    public Bound getPosition() {
        return bound;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }

    public Iterator<Entity> iterator() {
        return entities.iterator();
    }

    public void add(final Entity e) {
        entities.add(e);
        chan.broadcast(new Broadcastor<LayerChangeListener>() {
            public void apply(LayerChangeListener l) {
                l.onEntityAdded(e);
            }
        });
    }

    public void remove(final Entity e) throws NoSuchElementException {
        if(!entities.remove(e)){
            throw new NoSuchElementException("layer contains no entity "+e.getId());
        }

        chan.broadcast(new Broadcastor<LayerChangeListener>() {
            public void apply(LayerChangeListener l) {
                l.onEntityRemoved(e);
            }
        });
    }

    public Channel<LayerChangeListener> getLayerChannel() {
       return chan;
    }
}
