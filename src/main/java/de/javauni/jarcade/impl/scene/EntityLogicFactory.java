/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.scene;

import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.jarcade.model.scene.logic.EntityLogic;

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
