/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.state;

/**
 *
 * @param <S>
 * @author wabu
 */
public class StateModelEvent<S extends Enum<S>> {
    private final S state;

    public StateModelEvent(S state) {
        this.state = state;
    }

    public S getState() {
        return state;
    }
}
