/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.state;

import de.javauni.jarcade.event.Listener;

/**
 *
 * @param <S> state type
 * @author wabu
 */
public interface StateListener<S extends Enum<S>> extends Listener {
    void onStateChange(S state);
}
