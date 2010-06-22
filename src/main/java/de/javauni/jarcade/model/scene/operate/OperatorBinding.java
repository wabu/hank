package de.javauni.jarcade.model.scene.operate;

public class OperatorBinding<E> {
    private final Operator<E> op;
    private final E e;

    
    public OperatorBinding(E e, Operator<E> op) {
        this.e = e;
        this.op = op;
    }

    public void apply(long delta){ 
        op.step(e, delta);
    }
}
