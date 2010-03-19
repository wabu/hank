/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model.menu;

import com.google.inject.AbstractModule;

/**
 *
 * @author wabu
 */
public class MenuModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MainMenuAccess.class).to(MainMenuImpl.class);
        bind(MainMenuExport.class).to(MainMenuImpl.class);
    }

}
