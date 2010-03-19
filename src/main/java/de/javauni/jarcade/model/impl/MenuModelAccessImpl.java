/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.impl;

import com.google.inject.Inject;
import de.javauni.jarcade.model.MenuModel;
import de.javauni.jarcade.model.MenuModelAccess;

/**
 *
 * @author wabu
 */
public class MenuModelAccessImpl implements MenuModelAccess {
    private final MenuModel model;

    @Inject
    public MenuModelAccessImpl(MenuModel model) {
        this.model = model;
    }
}
