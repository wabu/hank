/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.yarrish.engine.model;

/**
 * @param <A> action type
 * @author wabu
 */
public interface Model<A> {

    void send(A action);
}
