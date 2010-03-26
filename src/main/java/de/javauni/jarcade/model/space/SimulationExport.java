/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.model.state.StateModelExport;

/**
 *
 * @author wabu
 */
public interface SimulationExport extends StateModelExport<SpacePhase>{
    Channel<SpaceChangeListener> getSpaceChannel();
    Space getSpace();
}
