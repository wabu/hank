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

package de.javauni.jarcade.model.scene;

import java.util.NoSuchElementException;

import com.google.common.base.Function;

import de.javauni.jarcade.geom.Bound;
import de.javauni.jarcade.model.entities.Entity;
import de.javauni.jarcade.model.impl.event.Channel;
import de.javauni.jarcade.model.scene.event.SceneChangeListener;

import edu.umd.cs.findbugs.annotations.CheckForNull;

/**
 * a layered 2d scene where things (entities) can live
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 * @see Layer
 */
public interface Scene {
    Bound getWorldSize();
    Viewport getViewport();

    Channel<SceneChangeListener> getSceneChannel();

    /**
     * get a list of all layers, starting with the layer with 
     * the biggist index
     * @return list of all layers
     */
    Iterable<? extends Layer> getLayers();
    /**
     * returns a layer with an given index
     * @param index
     * @return layer with index
     * @throws IndexOutOfBoundsException when there is no layer with the given index
     */
    Layer getLayer(int index) throws IndexOutOfBoundsException;
    /**
     * get the layer with the zero index, normally the main active layer
     * @return
     */
    Layer getZeroLayer();

    /**
     * @return all entities in the space starting with the backmost entities
     * @see Layer
     */
    Iterable<Entity> getAllEntities();

    /**
     * get an entity by id
     * @param id
     * @return entity or null
     */
    @CheckForNull
    Entity getEntity(int id);


    public interface Edit extends Scene {
        /**
         * adds a layer to the space. not
         * @param l
         * @throws IllegalArgumentException when space already contains layer with same index
         * @return layer index
         */
        int addLayer(Layer.Edit l) throws IllegalArgumentException;

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
    }
}
