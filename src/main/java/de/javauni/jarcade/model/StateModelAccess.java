/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model;

/**
 *
 * @author wabu
 */
public interface StateModelAccess<T extends Enum<T>> {
    void doTransition(T trans);
}
