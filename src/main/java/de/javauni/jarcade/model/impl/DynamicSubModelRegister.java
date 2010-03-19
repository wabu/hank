/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.impl;

import de.javauni.jarcade.model.Model;

/**
 *
 * @author wabu
 */
public interface DynamicSubModelRegister {
    <M extends Model> void registerModel(M m, Class<M> iface);
}
