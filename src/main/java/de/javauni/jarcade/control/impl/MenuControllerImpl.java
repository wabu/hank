/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.control.impl;

import com.google.inject.Inject;
import de.javauni.jarcade.control.MenuController;
import de.javauni.jarcade.model.MenuModelAccess;

/**
 *
 * @author wabu
 */
public class MenuControllerImpl implements MenuController {
    private final MenuModelAccess mma;

    @Inject
    public MenuControllerImpl(MenuModelAccess mma) {
        this.mma = mma;
    }

}
