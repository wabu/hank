/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model.main;

import com.google.inject.AbstractModule;
import de.javauni.utils.guice.ScopeManagerModule;

/**
 * guice submodule for the main state
 * @author wabu
 */
public class MainModelModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ScopeManagerModule());
        bind(MainModelAccess.class).to(MainModelImpl.class);
        bind(MainModelExport.class).to(MainModelImpl.class);
    }

}
