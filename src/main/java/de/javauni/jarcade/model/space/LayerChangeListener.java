/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

import de.javauni.jarcade.event.Listener;

/**
 *
 * @author covin
 */
public interface LayerChangeListener extends Listener {
    void onEntityAdded(Entity e);
    void onEntityRemoved(Entity e);
}
