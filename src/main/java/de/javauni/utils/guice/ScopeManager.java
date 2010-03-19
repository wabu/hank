/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.Inject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * manages the scope of classes annotated with @ManagedScope
 * only a single instance of mananged classes will be return inside a single scope
 * there's only one active scope, but when a scope gets reactivated,
 * instances of the scope will be reused.
 * instances inside a scope are lost when a scope is cleared
 * @param <E> enum for scopes
 * @author wabu
 * @see ManagedScope
 */
public class ScopeManager<E extends Enum<E>> {
    private final ManagedScopeImpl manager;

    private final Map<E, ScopeMap> scopes = new ConcurrentHashMap<E, ScopeMap>();

    @Inject
    public ScopeManager(ManagedScopeImpl manager) {
        this.manager = manager;
    }

    /**
     * activates a scope
     * @param key the scope
     */
    public void activateScope(E key) {
        if(!scopes.containsKey(key)) {
            scopes.put(key, new ScopeMap());
        }
        manager.setScope(scopes.get(key));
    }
    /**
     * leaves the current active scope, so no scope will be active anymore
     */
    public void leaveScope() {
        manager.setScope(null);
    }

    /**
     * clears all other scopes
     * @param key socpe that will not be cleared
     */
    public void clearOtherScopes(E key) {
        ScopeMap sm =
            scopes.containsKey(key) ? scopes.get(key) : new ScopeMap();
        manager.setScope(sm);
        scopes.clear();
        scopes.put(key, sm);
    }

    /**
     * clears some specific scopes
     * @param keys scopes that get cleared
     */
    public void clearScope(E ... keys) {
        for(E k : keys) {
            scopes.remove(k);
        }
    }
}
