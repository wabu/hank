package de.javauni.jarcade.model.scene.event;

import de.javauni.jarcade.model.entities.Entity;

/**
 * @author wabu
 */
public interface LayerChangeListener {
    void onEntityAdded(Entity e);
    void onEntityRemoved(Entity e);
}
