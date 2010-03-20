/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.world;

/**
 *
 * @author wabu
 */
public class SpaceChangeListenerAdapter implements SpaceChangeListener {
    public void onEntitySpawned(Entity e, Layer layer) {
        // this is an adapter class
    }

    public void onEntityRemoved(Entity e, Layer layer) {
        // this is an adapter class
    }

    public void onEntityLayerChange(Entity e, Layer before, Layer layer) {
        // this is an adapter class
    }
}
