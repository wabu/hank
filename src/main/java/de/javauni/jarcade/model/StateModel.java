/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model;

/**
 *
 * @param <S> state type
 * @param <T> transition type
 * @author wabu
 */
public interface StateModel<S extends Enum<S>, T extends Enum<T>>
        extends StateModelAccess<T>, StateModelExport<S> {

}
