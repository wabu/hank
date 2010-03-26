/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import de.javauni.jarcade.event.Broadcastor;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.model.space.LayerEdit;
import de.javauni.jarcade.model.space.Space;
import de.javauni.jarcade.model.space.SpaceChangeListener;
import de.javauni.jarcade.model.space.SpaceEdit;
import de.javauni.utils.IdList;
import de.javauni.utils.geom.Box;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author wabu
 */
public class SpaceImpl implements Space, SpaceEdit {
    private final Box size;
    private final IdList<Entity> entities;
    private final SortedMap<Integer, LayerEdit> layers;
    private final LayerEdit zero;
    private final Channel<SpaceChangeListener> chan;

    public SpaceImpl(
            Channel<SpaceChangeListener> chan,
            Box size, LayerEdit zero) {
        assert zero.getIndex() == 0;

        this.chan = chan;
        this.size = size;
        this.entities = new IdList<Entity>();
        this.layers = new TreeMap<Integer, LayerEdit>();
        this.zero = zero;
        layers.put(0,zero);
    }

    public Box getWorldBox() {
        return size;
    }

    public Channel<SpaceChangeListener> getSpaceChannel() {
        return chan;
    }

    public LayerEdit getLayer(int index) {
        if(!layers.containsKey(index)){
            throw new IndexOutOfBoundsException(
                    "no layer with index "+index+" in this space");
        }
        return layers.get(index);
    }

    public Iterable<? extends LayerEdit> getLayers() {
        return layers.values();
    }

    public LayerEdit getZeroLayer() {
        return zero;
    }

    public Entity getEntity(int id) {
        return entities.get(id);
    }

    public Iterable<Entity> getAllEntities() {
        return Iterables.filter(entities, Predicates.notNull());
    }

    public int addLayer(LayerEdit l) throws IllegalArgumentException {
        if(layers.containsKey(l.getIndex())) {
            throw new IllegalArgumentException(
                    "space already contains a layer with index "+l.getIndex());
        }
        layers.put(l.getIndex(), l);
        return l.getIndex();
    }

    public <E extends Entity> E addEntity(Function<Integer, E> construct, final int layerIndex)
            throws IndexOutOfBoundsException {
        final LayerEdit layer = getLayer(layerIndex);
        final E entity = entities.add(construct);
        layer.add(entity);

        chan.broadcast(new Broadcastor<SpaceChangeListener>() {
            public void apply(SpaceChangeListener l) {
                l.onEntitySpawned(entity, layer);
            }
        });
        return entity;
    }

    public void moveEntity(final Entity e, int oldLayer, int newLayer) throws NoSuchElementException, IndexOutOfBoundsException {
        final LayerEdit l1 = getLayer(oldLayer);
        final LayerEdit l2 = getLayer(newLayer);

        l1.remove(e);
        l2.add(e);

        chan.broadcast(new Broadcastor<SpaceChangeListener>() {
            public void apply(SpaceChangeListener l) {
                l.onEntityLayerChange(e, l1, l2);
            }
        });
    }

    public void removeEnity(final Entity e, int layerIndex) throws NoSuchElementException, IndexOutOfBoundsException {
        final LayerEdit layer = getLayer(layerIndex);
        layer.remove(e);

        chan.broadcast(new Broadcastor<SpaceChangeListener>() {
            public void apply(SpaceChangeListener l) {
                l.onEntitySpawned(e, layer);
            }
        });
    }
}
