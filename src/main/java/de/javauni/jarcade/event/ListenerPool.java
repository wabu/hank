/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author wabu
 */
public class ListenerPool {
    private final Map<Class<? extends Listener>, List<Listener>> map
            = new HashMap<Class<? extends Listener>, List<Listener>>();

    //TODO fix null pointer exception

    public <T extends Listener> void remove(Class<T> t, T l) {
        map.get(t).remove(l);
    }

    @SuppressWarnings("unchecked")
    public <T extends Listener> Iterable<T> getListeners(Class<T> t) {
        return (Iterable<T>) map.get(t);
    }

    public <T extends Listener> void add(Class<T> t, T l) {
        if(!map.containsValue(t)) {
            map.put(t, new CopyOnWriteArrayList<Listener>());
        }
        map.get(t).add(l);
    }

}
