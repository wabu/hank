/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.world;

import de.javauni.jarcade.event.Listener;

/**
 *
 * @author wabu
 */
public interface SpaceChangeListener extends Listener {
    void onEntitySpawned(Entity e, Layer layer);
    void onEntityRemoved(Entity e, Layer layer);

    void onEntityLayerChange(Entity e, Layer before, Layer layer);
}
