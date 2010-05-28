/*
 *  Copyright (C) 2010 Daniel Waeber <wabu@inf.fu-berlin.de>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package de.javauni.jarcade.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.GuardedBy;

import de.javauni.jarcade.model.event.Broadcastor;
import de.javauni.jarcade.model.event.Channel;

/**
 * default implementation of a model with a concrete main state
 * it will call the doTransition function as a request to change the state arrivates
 * @param <S> state type
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public abstract class AbstractStateModel<S extends Enum<S>>
        implements StateModelExport<S>, StateModelAccess<S> {
    private final Logger log = LoggerFactory.getLogger(AbstractStateModel.class);

    @GuardedBy("mutex")
    private S state;
    private final Object mutex = new Object();

    private final Channel<StateListener<S>> channel;

    public AbstractStateModel(Channel<StateListener<S>> channel, S initial) {
        this.channel = channel;
        this.state = initial;
    }

    @Override
    final public S getState() {
        return state;
    }

    @Override
    final public void setState(S state) throws IllegalArgumentException {
        synchronized(mutex) {
            S old = this.state;
            log.debug("trying to transite from "+old+" to "+ state);
            doStateTransition(old, state);
            log.debug("state transition from "+old+" to "+ state);
            this.state = state;
            doStateBroadcast(old, state);
        }
    }

    @Override
    final public Channel<StateListener<S>> getStateChannel() {
        return channel;
    }


    protected abstract void doStateTransition(S src, S tgt) throws IllegalArgumentException;

    protected void doStateBroadcast(final S src, final S tgt) {
        channel.broadcast(new Broadcastor<StateListener<S>>() {
            @Override
            public void apply(StateListener<S> l) {
                l.onStateChange(tgt);
            }
        });
    }
}
