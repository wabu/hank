/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.Inject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @param <E> enum for scopes
 * @author wabu
 */
public class ScopeManager<E extends Enum<E>> {
    private final ManagedScopeImpl manager;

    private final Map<E, ScopeMap> scopes = new ConcurrentHashMap<E, ScopeMap>();

    @Inject
    public ScopeManager(ManagedScopeImpl manager) {
        this.manager = manager;
    }

    public void activateScope(E key) {
        if(!scopes.containsKey(key)) {
            scopes.put(key, new ScopeMap());
        }
        manager.setScope(scopes.get(key));
    }
    public void leaveScope() {
        manager.setScope(null);
    }

    public void clearOtherScopes(E key) {
        ScopeMap sm =
            scopes.containsKey(key) ? scopes.get(key) : new ScopeMap();
        manager.setScope(sm);
        scopes.clear();
        scopes.put(key, sm);
    }

    public void clearScope(E key) {
        scopes.remove(key);
    }
}
