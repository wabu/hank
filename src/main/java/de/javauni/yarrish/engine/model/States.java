/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.yarrish.engine.model;

/**
 * interface for a state machine.
 * @param <S> state type
 * @param <T> transition type
 * @author wabu
 */
public interface States<S extends Enum<S>, T extends Enum<T>>  extends Model<T> {

    S getCurrentState();
}
