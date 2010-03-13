/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @param <E> event type
 * @author wabu
 */
public class ChannelImpl<E> implements Channel<E> {
    private final List<Listener<E>> ls = new CopyOnWriteArrayList<Listener<E>>();

    public void broadcast(E event) {
        for(Listener<E> l : ls) {
            l.on(event);
        }
    }

    public void suscribe(Listener<E> listener) {
        ls.add(listener);
    }

    public void unsubscribe(Listener<E> listener) {
        ls.remove(listener);
    }

}
