/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

import de.javauni.utils.HavingId;
import de.javauni.utils.geom.Box;

/**
 * an entity simply is an object in the virtual space.
 * its called entity because the name object would collide with the concept
 * of objects inside java.
 * @author wabu
 * @see Space
 */
public interface Entity extends HavingId {
    Box getPositionBox();

    /**
     * all entities get numbered, so they can be easyly compared, hashed
     * or looked up inside an arraylist
     * @return id of the entity
     */
    int getId();
}
