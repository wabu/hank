/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

import de.javauni.jarcade.event.Listener;

/**
 *
 * @author wabu
 */
public interface SpacePhaseListener extends Listener {
    void onPhaseChange(SpacePhase phase, SpacePhase before);
}
