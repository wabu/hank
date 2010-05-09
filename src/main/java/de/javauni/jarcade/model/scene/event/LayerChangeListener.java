package de.javauni.jarcade.model.scene.event;

import de.javauni.jarcade.model.event.Listener;
import de.javauni.jarcade.model.scene.Entity;

/**
 * @author wabu
 */
public interface LayerChangeListener extends Listener {
    void onEntityAdded(Entity e);
    void onEntityRemoved(Entity e);
}
