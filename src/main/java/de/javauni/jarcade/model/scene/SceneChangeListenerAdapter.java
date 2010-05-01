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

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class SceneChangeListenerAdapter implements SceneChangeListener {
    public void onLayerAdded(Layer layer) {
        // this is an adapter class
    }

    @Override
    public void onEntitySpawned(Entity e, Layer layer) {
        // this is an adapter class
    }

    @Override
    public void onEntityRemoved(Entity e, Layer layer) {
        // this is an adapter class
    }

    @Override
    public void onEntityLayerChange(Entity e, Layer before, Layer layer) {
        // this is an adapter class
    }

}
