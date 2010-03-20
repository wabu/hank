/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space.logic;

import com.google.inject.ImplementedBy;
import de.javauni.jarcade.impl.space.LogicManagerImpl;
import de.javauni.jarcade.model.space.Entity;

/**
 *
 * @author wabu
 */
@ImplementedBy(LogicManagerImpl.class)
public interface LogicManager {
    <E extends Entity> void addEntityLogic(E entity, EntityLogic<E> logic);
    void removeEntityLogic(Entity entity);

    void updateStep(long timeDelta);
}
