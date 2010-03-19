/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.menu;

/**
 * @author wabu
 */
public interface MenuModelAccess {
    void select(int i);
    void selectNext();
    void selectPrevious();

    void submit();
    void submit(int i);
}
