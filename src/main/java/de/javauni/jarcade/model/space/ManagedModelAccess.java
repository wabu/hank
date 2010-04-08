/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

import de.javauni.jarcade.model.state.StateModelAccess;
import java.io.IOException;

/**
 *
 * @author wabu
 */
public interface ManagedModelAccess extends StateModelAccess<SpacePhase>{
    /**
     * initializes the world by loading it from a given ressources
     * @param ressources ressources location
     * @throws IllegalStateException when already initialized
     * @throws IOException when level can't be loaded
     */
    void initialize(String ressources) throws IllegalStateException, IOException;
}
