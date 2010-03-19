/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.state;

import de.javauni.jarcade.event.Listener;

/**
 *
 * @param <S>
 * @author wabu
 */
public interface StateModelListener<S extends Enum<S>> extends Listener {

    void onModelStateChange(StateModelEvent<S> ev);
}
