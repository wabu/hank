/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.event;

/**
 *
 * @param <L> type of listeners
 * @author wabu
 */
public interface Observable<L extends Listener> {
    void addListener(L listener);
    void removeListener(L listener);
}
