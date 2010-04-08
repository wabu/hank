/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.model.space.logic.EntityLogic;
import javax.annotation.CheckForNull;

/**
 *
 * @author covin
 */
class EntityLogicFactory {
    @CheckForNull
    public <E extends Entity> EntityLogic<E> getEntityLogic(E entity) {
        return null;
    }
}
