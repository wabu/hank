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

package de.javauni.jarcade.model.space.logic;

import com.google.inject.ImplementedBy;
import de.javauni.jarcade.impl.space.EntityHandlerImpl;
import de.javauni.jarcade.model.space.Entity;

/**
 * An EntityHandler just invokes an update on every active(?) entity
 *
 * @author wabu
 */
@ImplementedBy(EntityHandlerImpl.class)
public interface EntityHandler {

    @Deprecated
    <E extends Entity> void addEntityLogic(E entity, EntityLogic<E> logic);
    @Deprecated
    void removeEntityLogic(Entity entity);
    
    /**
     * @param timeDelta milliseconds since last <code>updateEntities</code>
     *         invocation
     */
    void updateEntities(long timeDelta);
}
