/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.level;

import de.javauni.utils.geom.Box;

/**
 * an entity is a virtual world object.
 * its called entity because the name object would collide with the concept
 * of objects inside java.
 * @author wabu
 */
public interface Entity {
    Box getPositionBox();
}
