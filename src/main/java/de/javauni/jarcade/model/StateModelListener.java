/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model;

/**
 *
 * @author wabu
 */
public interface StateModelListener<S extends Enum<S>> {
    <M extends ModelAccess & ModelExport>
            void onModelStateChange(StateModelEvent<S, M> ev);
}
