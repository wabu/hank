/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.event;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author wabu
 */
public class ListenerPool<L extends Listener> implements Iterable<L> {
    private final List<L> listeners = new CopyOnWriteArrayList<L>();

    public void add(L listener) {
        listeners.add(listener);
    }
    public void remove(L listener) {
        listeners.remove(listener);
    }

    public Iterator<L> iterator() {
        return listeners.iterator();
    }
}
