/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.event;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/**
 * @param <L> listener type
 * @author wabu
 */
class ChannelImpl<L extends Listener> implements Channel<L> {
    private final List<L> ls = new CopyOnWriteArrayList<L>();
    private final Executor exec;

    @Inject
    ChannelImpl(@Named("channel-broadcast-executor") Executor exec) {
        this.exec = exec;
    }

    public void addListener(L l) {
        ls.add(l);
    }

    public void removeListener(L l) {
        ls.remove(l);
    }

    public void broadcast(final Broadcastor<L> bc) {
        exec.execute(new Runnable() {
            public void run() {
                for(L l : ls) {
                    bc.apply(l);
                }
            }
        });
    }

}
