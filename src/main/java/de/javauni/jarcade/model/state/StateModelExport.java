/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.jarcade.model.state;

import de.javauni.jarcade.event.Channel;

/**
 * exports the state of a model with a concreate main state
 * @param <S> state type
 * @author wabu
 */
public interface StateModelExport<S extends Enum<S>> {
    Channel<StateListener<S>> getStateChannel();

    S getState();
}
