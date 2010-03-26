/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

/**
 * access interface for an active entity
 * @param <A> activity enum type
 * @author wabu
 * @see Actity
 */
public interface ActityAccess<A extends Enum<A>> {
    void setActity(A activity);
}
