package de.javauni.utils.state;

/**
 *
 * @author wabu
 */
public interface TargetBuilder<S extends Enum<S>, T extends Enum<T>>  {

    StatesTransitionBuilder<S, T> to(S tgt);
}
