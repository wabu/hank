/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import de.javauni.jarcade.model.MenuModel;
import de.javauni.jarcade.model.state.StateModelAccess;
import de.javauni.jarcade.model.state.StateModelExport;
import de.javauni.utils.guice.ScopeManagerModule;
import de.javauni.yarrish.model.states.ModelState;

/**
 *
 * @author wabu
 */
public class YarrishModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ScopeManagerModule());

        bind(new TypeLiteral<StateModelExport<ModelState>>(){})
                .to(YarrishModel.class);
        bind(new TypeLiteral<StateModelAccess<ModelState>>(){})
                .to(YarrishModel.class);

        bind(MenuModel.class).to(YarrishMenu.class);
    }

}
