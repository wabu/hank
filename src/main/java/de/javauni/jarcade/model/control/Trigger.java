package de.javauni.jarcade.model.control;

public interface Trigger<A extends Enum<A>> {
    void trigger(A action);
}
