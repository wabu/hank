/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.Key;
import com.google.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wabu
 */
class ScopeMap {
    private final Map<Key<?>, Object> scoped = new HashMap<Key<?>, Object>();

    @SuppressWarnings("unchecked")
    public <T> T get(Key<T> key, Provider<T> prvdr) {
        if(!scoped.containsKey(key)) {
            scoped.put(key, prvdr.get());
        }
        return (T) scoped.get(key);
    }
}
