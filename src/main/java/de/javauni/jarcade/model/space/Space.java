/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

import de.javauni.utils.geom.Box;
import edu.umd.cs.findbugs.annotations.CheckForNull;

/**
 * a layered 2d space where things (entities) can live
 * @author wabu
 * @see Layer
 */
public interface Space {
    Box getWorldBox();

    /**
     * get a list of all layers, starting with the layer with 
     * the biggist index
     * @return list of all layers
     */
    Iterable<? extends Layer> getLayers();
    /**
     * returns a layer with an given index
     * @param index
     * @return layer with index
     * @throws IndexOutOfBoundsException when there is no layer with the given index
     */
    Layer getLayer(int index) throws IndexOutOfBoundsException;
    /**
     * get the layer with the zero index
     * @return
     */
    Layer getZeroLayer();

    /**
     * @return all entities in the space starting with the backmost entities
     * @see Layer
     */
    Iterable<Entity> getAllEntities();

    /**
     * get an entity by id
     * @param id
     * @return entity or null
     */
    @CheckForNull
    Entity getEntity(int id);
}
