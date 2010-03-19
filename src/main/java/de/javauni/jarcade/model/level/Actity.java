/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.level;

/**
 * an entity that has a current activity
 * @author wabu
 */
public interface Actity<A extends Enum<A>> {
    A getCurrentActivity();
}
