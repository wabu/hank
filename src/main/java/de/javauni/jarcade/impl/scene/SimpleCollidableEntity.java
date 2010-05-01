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

package de.javauni.jarcade.impl.scene;

import de.javauni.jarcade.model.scene.entity.CollidableEntity;
import de.javauni.utils.geom.Box;
import de.javauni.utils.props.ImpliedProperty;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class SimpleCollidableEntity extends SimpleEntity implements CollidableEntity {
    private final Box collision;

    public SimpleCollidableEntity(int id, Box pos) {
        super(id, pos);
        collision = pos;
    }

    public SimpleCollidableEntity(int id,
            @ImpliedProperty(name="position") Box pos,
            @ImpliedProperty(name="collision") Box collision) {
        super(id, pos);
        this.collision = collision;
    }

    public Box getCollisionBox() {
        return collision;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " at " + getPositionBox();
    }
}
