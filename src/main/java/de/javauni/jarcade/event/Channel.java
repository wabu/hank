/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.event;

import com.google.inject.ImplementedBy;

/**
 * Listeners will be notified about changes inside the channel.
 * data send to the channel will all be run inside a single thread
 * @param <L> the listener interface
 * @author wabu
 */
@ImplementedBy(ChannelImpl.class)
public interface Channel<L extends Listener> {
    void addListener(L listener);
    void removeListener(L listener);

    void broadcast(Broadcastor<L> bc);
}
