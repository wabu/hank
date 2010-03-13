/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.jarcade.model;

import de.javauni.jarcade.impl.states.AbstractStates;

/**
 * interface for a state machine.
 * @param <S> state type
 * @param <T> transition type
 * @author wabu
 * @see AbstractStates
 */
public interface States<S extends Enum<S>, T extends Enum<T>>  extends Model<T> {

    S getCurrentState();
}
