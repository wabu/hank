/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

import de.javauni.jarcade.event.Channel;

/**
 * the virtual space consists of different layers where all the entities live.
 * each layer has an index to specifiy the order in which the layers a shown
 * and a distance inside the game world. the layer with index 0 normally
 * is the main layer, where most of the game events happen
 * @author wabu
 */
public interface Layer extends Iterable<Entity>{
    Channel<LayerChangeListener> getLayerChannel();
    /**
     * smaller index means layer is ontop of other layer
     * @return index of the layer
     */
    int getIndex();
    /**
     * specifices how far away a layer is
     * this will affact the relative movement speed of the layer
     * @return distance of the layer
     */
    int getDistance();
}
