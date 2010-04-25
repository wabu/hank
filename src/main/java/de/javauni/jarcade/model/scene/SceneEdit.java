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

import de.javauni.jarcade.model.scene.entity.Entity;
import com.google.common.base.Function;
import java.util.NoSuchElementException;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public interface SceneEdit extends Scene {
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
}

