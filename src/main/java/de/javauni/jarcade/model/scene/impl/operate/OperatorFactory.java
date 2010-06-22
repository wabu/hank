package de.javauni.jarcade.model.scene.impl.operate;

import de.javauni.jarcade.model.scene.Operator;

public interface OperatorFactory<E, O extends Operator<E>> {
    O create(E e);
}
