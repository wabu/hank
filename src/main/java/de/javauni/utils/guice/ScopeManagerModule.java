/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.AbstractModule;

/**
 * guice modle to bind scope manager
 * @author wabu
 */
public class ScopeManagerModule extends AbstractModule {
    @Override
    protected void configure() {
        ManagedScopeImpl mgr = new ManagedScopeImpl();
        bind(ManagedScopeImpl.class).toInstance(mgr);
        bindScope(ManagedScope.class, mgr);
        bind(ScopeManager.class);
    }

}
