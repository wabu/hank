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

package de.javauni.jarcade.model.scene.entity;

import de.javauni.utils.HavingId;
import de.javauni.utils.geom.Box;

/**
 * an entity simply is an object in the virtual space.
 * its called entity because the name object would collide with the concept
 * of objects inside java.
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 * @see Space
 */
public interface Entity extends HavingId {
    Box getPositionBox();

    /**
     * all entities get numbered, so they can be easyly compared, hashed
     * or looked up inside an arraylist
     * @return id of the entity
     */
    int getId();
}
