/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.jarcade.impl.states;

import java.util.Map;

/**
 *
 * @author wabu
 */
public interface StatesMap<S extends Enum<S>, T extends Enum<T>>  {
    Map<S, Map<T,S>> getMap();
    S getStart();
}
