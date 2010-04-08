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

import de.javauni.jarcade.model.space.Actity;
import de.javauni.jarcade.model.space.ActityAccess;
import de.javauni.utils.geom.Box;

/**
 * @param <A> activity enum type
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class SimpleActiveEntity<A extends Enum<A>>
        extends SimpleCollidableEntity implements Actity<A>, ActityAccess<A> {

    private A activity;

    public SimpleActiveEntity(int id, Box pos, Box collision, A activity) {
        super(id, pos, collision);
        this.activity = activity;
    }

    public SimpleActiveEntity(int id, Box pos, A activity) {
        super(id, pos);
        this.activity = activity;
    }

    public A getCurrentActivity() {
        return activity;
    }

    public void setActity(A activity) {
        this.activity = activity;
    }

}
