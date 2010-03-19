/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish;

import com.google.inject.AbstractModule;
import de.javauni.yarrish.model.main.MainModelModule;
import de.javauni.yarrish.model.menu.MenuModule;
import de.javauni.yarrish.view.DummyView;

/**
 *
 * @author wabu
 */
public class YarrishModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new MainModelModule());
        install(new MenuModule());

        bind(DummyView.class);
    }

}
