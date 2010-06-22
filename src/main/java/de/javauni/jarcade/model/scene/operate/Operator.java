package de.javauni.jarcade.model.scene.operate;

public interface Operator<E> {
    void step(E e, long delta);
}
