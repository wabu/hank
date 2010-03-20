/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.world;

import de.javauni.utils.geom.Box;
import java.util.List;

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
    List<Layer> getLayers();
    /**
     * get the layer with the zero index
     * @return
     */
    Layer getZeroLayer();

    /**
     * @return all entities in the space starting with the backmost entities
     * @see Layer
     */
    List<Entity> getAllEntities();
}
