/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.state;

import com.google.inject.Inject;
import de.javauni.jarcade.event.Broadcastor;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.exceptions.IllegalAction;
import javax.annotation.concurrent.GuardedBy;

/**
 * default implementation of a model with a concrete main state
 * it will call the doTransition function as a request to change the state arrivates
 * @param <S> state type
 * @author wabu
 */
public abstract class AbstractStateModel<S extends Enum<S>>
        implements StateModelExport<S>, StateModelAccess<S> {

    @GuardedBy("mutex")
    private S state;
    private final Object mutex = new Object();

    private final Channel<StateListener<S>> channel;

    @Inject
    public AbstractStateModel(Channel<StateListener<S>> channel) {
        this.channel = channel;
    }

    public S getState() {
        return state;
    }

    public void setState(S state) {
        synchronized(mutex) {
            S old = this.state;
            doStateTransition(old, state);
            this.state = state;
            doStateBroadcast(old, state);
        }
    }

    public Channel<StateListener<S>> getStateChannel() {
        return channel;
    }


    protected abstract void doStateTransition(S src, S tgt) throws IllegalAction;

    protected void doStateBroadcast(S src, S tgt) {
        channel.broadcast(new Broadcastor<StateListener<S>>() {
            public void apply(StateListener<S> l) {
                l.onStateChange(state);
            }
        });
    }
}
