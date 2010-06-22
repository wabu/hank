package de.javauni.jarcade.model.scene.operate;

public interface OperatorFactory<E, O extends Operator<E>> {
    O create(E e);
}
