/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.yarrish.engine.impl.states;

/**
 *
 * @author wabu
 */
public interface StatesTransitionBuilder<S extends Enum<S>, T extends Enum<T>>
        extends StatesBuilder<S, T>, TransitionBuilder<S, T> {
}
