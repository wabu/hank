/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.state;

import de.javauni.jarcade.event.ListenerPool;
import de.javauni.jarcade.exceptions.IllegalAction;

/**
 *
 * @param <S> state type
 * @author wabu
 */
public abstract class AbstractStateModelImpl<S extends Enum<S>>
        implements StateModelExport<S>, StateModelAccess<S> {
    private S state;

    private final ListenerPool<StateListener<S>> ls
            = new ListenerPool<StateListener<S>>();

    public S getState() {
        return state;
    }

    public void addListener(StateListener<S> listener) {
        ls.add(listener);
    }

    public void removeListener(StateListener<S> listener) {
        ls.remove(listener);
    }

    public void setState(S state) {
        doStateTransition(this.state, state);
        this.state = state;
    }

    protected abstract void doStateTransition(S src, S tgt) throws IllegalAction;

    protected void broadcastState(S state) {
        // TODO event threads???
        for(StateListener<S> l : ls) {
            l.onStateChange(new StateEvent<S>(state));
        }
    }
}
