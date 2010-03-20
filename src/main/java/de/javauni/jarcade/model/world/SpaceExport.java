/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.world;

import de.javauni.jarcade.event.Channel;

/**
 *
 * @author wabu
 */
public interface SpaceExport {
    Channel<SpaceChangeListener> getSpaceChannel();
    Channel<SpacePhaseListener> getPhaseChannel();

    Space getSpace();
}
