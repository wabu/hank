/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.state;

import com.google.inject.Inject;
import de.javauni.jarcade.event.Broadcastor;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.exceptions.IllegalAction;

/**
 *
 * @param <S> state type
 * @author wabu
 */
public abstract class AbstractStateModel<S extends Enum<S>>
        implements StateModelExport<S>, StateModelAccess<S> {
    private S state;
    private final Channel<StateListener<S>> channel;

    @Inject
    public AbstractStateModel(Channel<StateListener<S>> channel) {
        this.channel = channel;
    }

    public S getState() {
        return state;
    }


    public void setState(S state) {
        S old = this.state;
        doStateTransition(old, state);
        this.state = state;
        doStateBroadcast(old, state);
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
