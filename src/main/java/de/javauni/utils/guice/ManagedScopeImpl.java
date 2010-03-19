/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import javax.annotation.CheckForNull;

public class ManagedScopeImpl implements Scope {
    @CheckForNull private ScopeMap scope;

    public <T> Provider<T> scope(final Key<T> key, final Provider<T> prvdr) {
        return new Provider<T>() {
            @SuppressWarnings("unchecked")
            public T get() {
                ScopeMap sm = scope;
                if(sm == null) {
                    return prvdr.get();
                } else {
                    return sm.get(key, prvdr);
                }
            }
        };
    }

    void setScope(ScopeMap scope) {
        this.scope = scope;
    }
}
