package de.javauni.jarcade.impl.scene;

import de.javauni.jarcade.model.scene.operate.Operator;

public class OpBinding<E> {
    private final Operator<E> op;
    private final E e;

    
    public OpBinding(E e, Operator<E> op) {
        this.e = e;
        this.op = op;
    }

    void apply(long delta){ 
        op.step(e, delta);
    }
}
