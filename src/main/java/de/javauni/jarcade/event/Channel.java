/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.event;

/**
 * channel that broadcast events
 * @param <E> event type
 * @author wabu
 */
public interface Channel<E> {
    void broadcast(E event);
    void suscribe(Listener<E> listener);
    void unsubscribe(Listener<E> listener);
}
