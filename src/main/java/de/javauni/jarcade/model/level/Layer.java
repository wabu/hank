/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.level;

import java.util.List;

/**
 * the virtual world consists of different layers where all the entities live.
 * each layer has an index to specifiy the order in which the layers a shown
 * and a distance inside the game world.
 * @author wabu
 */
public interface Layer {
    /**
     * smaller index means layer is ontop of other layer
     * collissions will only be callculated on layer 0
     * @return index of the layer
     */
    int getIndex();
    /**
     * specifices how far away a layer is
     * this will affact the relative movement speed of the layer
     * @return distance of the layer
     */
    int getDistance();

    List<? extends Entity> getEntities();
}
