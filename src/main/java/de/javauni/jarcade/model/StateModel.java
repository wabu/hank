package de.javauni.jarcade.model;

import de.javauni.jarcade.model.impl.event.Channel;

public interface StateModel<S> extends Model {
    void setState(S state) throws IllegalStateException;
    S getState();

    Channel<ChangeListener<S>> getStateChangeChannel();

    interface ChangeListener<S> {
        void onStateChange(S src, S tgt);
    }
}
