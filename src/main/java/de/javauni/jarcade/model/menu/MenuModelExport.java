/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.menu;

import de.javauni.jarcade.model.state.StateModelAccess;
import java.util.List;

/**
 *
 * @param <S> submenu type
 * @author wabu
 */
public interface MenuModelExport<S extends Enum<S>> extends StateModelAccess<S>{
    List<? extends MenuItem> getItems();
    MenuItem getSelectedItem();
}
