/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model;

/**
 *
 * @author wabu
 */
public class StateModelEvent<S extends Enum<S>,
        M extends ModelAccess & ModelExport> {
    private final S state;
    private final M model;

    public StateModelEvent(S state, M model) {
        this.state = state;
        this.model = model;
    }

    public M getModel() {
        return model;
    }

    public S getState() {
        return state;
    }
}
