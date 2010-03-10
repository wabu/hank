/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.engine.model;

/**
 *
 * @param <E> states enum type
 * @author wabu
 */
public interface States<E extends Enum<E>> {
    E getCurrentState();
}
