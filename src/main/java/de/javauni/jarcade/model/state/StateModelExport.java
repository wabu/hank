/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.jarcade.model.state;

import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.model.ModelExport;

/**
 *
 * @param <S> state type
 * @author wabu
 */
public interface StateModelExport<S extends Enum<S>> extends ModelExport {
    Channel<StateListener<S>> getStateChannel();

    S getState();
}
