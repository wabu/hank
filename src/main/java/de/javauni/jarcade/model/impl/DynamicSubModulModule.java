/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.impl;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import de.javauni.jarcade.control.MenuController;
import de.javauni.jarcade.control.impl.MenuControllerImpl;
import de.javauni.jarcade.model.MenuModel;
import de.javauni.jarcade.model.MenuModelAccess;
import de.javauni.jarcade.model.Model;

/**
 *
 * @author wabu
 */
public class DynamicSubModulModule extends AbstractModule implements DynamicSubModelRegister {
    Class<?> currentIface;
    Model currentModel;

    @Override
    protected void configure() {
        bind(MenuController.class).to(MenuControllerImpl.class);
        bind(MenuModelAccess.class).to(MenuModelAccessImpl.class);
    }

    @Provides
    MenuModel provideMenuModel() {
        if(MenuModel.class.equals(MenuModel.class)) {
            return (MenuModel) currentModel;
        }
        return null;
    }

    public <M extends Model> void registerModel(M m, Class<M> iface) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
