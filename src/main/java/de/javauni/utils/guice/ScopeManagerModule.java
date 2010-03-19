/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.AbstractModule;

/**
 *
 * @author wabu
 */
public class ScopeManagerModule extends AbstractModule {
    @Override
    protected void configure() {
        InstanceScopeManager mgr = new InstanceScopeManager();
        bind(InstanceScopeManager.class).toInstance(mgr);
        bindScope(ManagedScope.class, mgr);
    }

}
