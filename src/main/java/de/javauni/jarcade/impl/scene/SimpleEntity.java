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

import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.utils.geom.Box;
import de.javauni.utils.props.ImpliedProperty;

/**
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class SimpleEntity implements Entity {
    private final Box pos;
    private final int id;

    public SimpleEntity(int id,
            @ImpliedProperty(name="position") Box pos) {
        this.pos = pos;
        this.id = id;
    }

    public Box getPositionBox() {
        return pos;
    }
    public int getId() {
        return id;
    }
}
