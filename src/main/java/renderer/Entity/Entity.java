/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package renderer.Entity;


/**
 * an entity simply is an object in the virtual space.
 * its called entity because the name object would collide with the concept
 * of objects inside java.
 * @author wabu
 * @see Space
 */
public interface Entity {
    Box getPositionBox();

    /**
     * all entities get numbered, so they can be easyly compared, hashed
     * or looked up inside an arraylist
     * @return id of the entity
     */
    int getId();
}
