/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

import com.google.common.base.Function;
import de.javauni.jarcade.event.Channel;
import java.util.NoSuchElementException;

/**
 *
 * @author wabu
 */
public interface SpaceEdit extends Space {
    /**
     * adds a layer to the space. not
     * @param l
     * @throws IllegalArgumentException when space already contains layer with same index
     * @return layer index
     */
    int addLayer(LayerEdit l) throws IllegalArgumentException;

    /**
     * adds a entity to the space
     * @param <E> type of the entity
     * @param construct function to create the entity with a give enitity id
     * @param layerIndex index of the layer, on which the entity will be put
     * @return the entity returned by consturct
     * @throws IndexOutOfBoundsException when there there is no layer with a given index
     */
    <E extends Entity> E addEntity(Function<Integer, E> construct, int layerIndex)
            throws IndexOutOfBoundsException;

    /**
     * move an entity onto another layer
     * @param e
     * @param oldLayer
     * @param newLayer
     * @throws NoSuchElementException when no entity e is found on layer oldLayer
     * @throws IndexOutOfBoundsException when there there is no layer with a given index
     */
    void moveEntity(Entity e, int oldLayer, int newLayer)
            throws NoSuchElementException, IndexOutOfBoundsException;

    /**
     * removes a entity form the world
     * @param e
     * @param layerIndex
     * @throws NoSuchElementException when no entity e is found on the layer
     * @throws IndexOutOfBoundsException when there there is no layer with a given index
     */
    void removeEnity(Entity e, int layerIndex)
            throws NoSuchElementException, IndexOutOfBoundsException;

    Channel<SpaceChangeListener> getSpaceChannel();
}
