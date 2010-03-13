/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model;

import com.google.inject.TypeLiteral;
import de.javauni.jarcade.event.Channel;
import de.javauni.utils.guice.ExtendedModule;
import de.javauni.yarrish.view.MainView;

/**
 *
 * @author wabu
 */
public class YarrishModule extends ExtendedModule {
    @Override
    protected void configure() {
        bind(MainStates.class);
        bindDynamic(new TypeLiteral<Channel<MainStates.State>>(){});

        bind(MenuModel.class);
        bind(LvlModel.class);

        bind(MainView.class);
    }
}
