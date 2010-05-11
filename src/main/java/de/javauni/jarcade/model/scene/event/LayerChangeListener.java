package de.javauni.jarcade.model.scene.event;

import de.javauni.jarcade.model.entities.Entity;
import de.javauni.jarcade.model.event.Listener;

/**
 * @author wabu
 */
public interface LayerChangeListener extends Listener {
    void onEntityAdded(Entity e);
    void onEntityRemoved(Entity e);
}
