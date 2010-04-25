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

import de.javauni.jarcade.event.Channel;

import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.utils.geom.Box;
import edu.umd.cs.findbugs.annotations.CheckForNull;

/**
 * a layered 2d scene where things (entities) can live
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 * @see Layer
 */
public interface Scene {
    Box getWorldBox();
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
     * get the layer with the zero index
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
}
