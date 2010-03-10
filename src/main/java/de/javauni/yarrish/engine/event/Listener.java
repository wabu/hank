/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.engine.event;

/**
 *
 * @param <E> event type
 * @author wabu
 */
public interface Listener<E> {
    void listen(E event);
}
