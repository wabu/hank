/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space.logic;

import com.google.inject.ImplementedBy;
import de.javauni.jarcade.impl.space.EntityHandlerImpl;
import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.model.space.SpaceEdit;

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
