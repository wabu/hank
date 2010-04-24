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
import de.javauni.jarcade.event.Channel;

/**
 * the virtual space consists of different layers where all the entities live.
 * each layer has an index to specifiy the order in which the layers a shown
 * and a distance inside the game world. the layer with index 0 normally
 * is the main layer, where most of the game events happen
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public interface Layer extends Iterable<Entity>{
    Channel<LayerChangeListener> getLayerChannel();
    /**
     * smaller index means layer is ontop of other layer
     * @return index of the layer
     */
    int getIndex();
    /**
     * specifices how far away a layer is
     * this will affact the relative movement speed of the layer
     * @return distance of the layer
     */
    int getDistance();
}
