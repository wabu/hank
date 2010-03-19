/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.level;

import de.javauni.utils.geom.Box;

/**
 * an entity that will collide with other objects
 * @author wabu
 */
public interface Collity extends Entity {
    Box getCollisionBox();
}
