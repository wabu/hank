/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.yarrish.engine.impl.states;

/**
 *
 * @author wabu
 */
public interface StatesBuilder<S extends Enum<S>, T extends Enum<T>>  {
    TransitionBuilder<S, T> from(S... src);
    StatesMap<S, T> start(S start);
}
