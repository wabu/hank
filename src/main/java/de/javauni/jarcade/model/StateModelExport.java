/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model;

/**
 *
 * @param <S> state type
 * @author wabu
 */
public interface StateModelExport<S extends Enum<S>> extends ModelExport {
    S getCurrentState();

    void subscribe(StateModelListener<S> listener);
    void unsubscribe(StateModelListener<S> listener);
}
