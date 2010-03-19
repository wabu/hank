/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.state;

import de.javauni.jarcade.exceptions.IllegalAction;

/**
 * @author wabu
 */
public interface StateModelAccess<S extends Enum<S>> {
    void setState(S state) throws IllegalAction;
}
