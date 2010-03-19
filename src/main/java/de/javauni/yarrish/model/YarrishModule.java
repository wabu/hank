/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model;

import com.google.inject.AbstractModule;
import de.javauni.yarrish.model.main.MainModelModule;
import de.javauni.yarrish.model.menu.MenuModule;

/**
 *
 * @author wabu
 */
public class YarrishModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new MainModelModule());
        install(new MenuModule());
    }

}
