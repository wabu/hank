/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.TypeLiteral;
import javax.annotation.CheckForNull;
import org.omg.CORBA.INVALID_ACTIVITY;

/**
 * you can use the instancescopemanager to manage the lifetime of objects
 * with the @ManagedScope anntiation
 * @author wabu
 * @see ManagedScope
 */
public class InstanceScopeManager implements Scope {
    public final BiMap<Key<?>, Object> scoped = HashBiMap.create();

    public <T> Provider<T> scope(final Key<T> key, final Provider<T> prvdr) {
        return new Provider<T>() {
            @SuppressWarnings("unchecked")
            public T get() {
                if(!scoped.containsKey(key)) {
                    scoped.put(key, prvdr.get());
                }
                return (T) scoped.get(key);
            }
        };
    }

    /**
     * invalidates the inistance for the given key, so further 
     * injections for the key will give you a new instance
     * @param <T> instance type
     * @param key guice key
     * @return current instance
     */
    @SuppressWarnings("unchecked")
    @CheckForNull
    public <T> T invalidate(final Key<T> key) {
        return (T) scoped.remove(key);
    }

    @CheckForNull
    public <T> T invalidate(final Class<T> type) {
        return invalidate(Key.get(type));
    }

    @CheckForNull
    public <T> T invalidate(final TypeLiteral<T> lit) {
        return invalidate(Key.get(lit));
    }
}
