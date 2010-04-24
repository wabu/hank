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

package de.javauni.jarcade.impl.space;

import de.javauni.jarcade.model.space.act.Movement;
import de.javauni.jarcade.model.space.act.Moving;
import de.javauni.jarcade.model.space.props.ImpliedProperty;
import de.javauni.jarcade.model.space.props.Property;
import de.javauni.utils.geom.Box;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class SimpleMovingActity extends SimpleActiveEntity<Movement> implements Moving {
    private final float speed;

    public SimpleMovingActity(
            int id,
            @ImpliedProperty(name="position") Box pos,
            @ImpliedProperty(name="collision") Box collision,
            @Property(name="movement speed", value="1f") float speed) {
        super(id, pos, collision, Movement.none);
        this.speed = speed;
    }

    public float getMovementSpeed() {
        return speed;
    }
}