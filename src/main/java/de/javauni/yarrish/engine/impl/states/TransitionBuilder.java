package de.javauni.yarrish.engine.impl.states;

/**
 *
 * @author wabu
 */
public interface TransitionBuilder<S extends Enum<S>, T extends Enum<T>>  {

    TargetBuilder<S, T> on(T... trans);
}
