package de.javauni.jarcade.model.scene;

public interface Operator<E> {
    void step(E e, long delta);
}
