/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @param <L> listener type
 * @author wabu
 */
public class ChannelImpl<L extends Listener> implements Channel<L> {
    private final List<L> ls = new CopyOnWriteArrayList<L>();
    private final Executor exec = Executors.newSingleThreadExecutor();


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
