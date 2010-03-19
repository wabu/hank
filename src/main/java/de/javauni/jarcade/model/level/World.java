/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.level;

import de.javauni.utils.geom.Box;
import java.util.List;

/**
 * a simple layered 2d world
 * @author wabu
 */
public interface World {
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
}
