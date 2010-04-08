/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space.logic;

import de.javauni.jarcade.model.space.Entity;

/**
 * @param <E> entity
 * @author wabu
 */
public interface EntityLogic<E extends Entity> {
    void update(E entity, long timeDelta);
}
