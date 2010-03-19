/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.level;

import java.util.List;

/**
 * a simple 2d world with many layers
 * @author wabu
 */
public interface World {
    List<Layer> getLayers();
    Layer getZeroLayer();
}
