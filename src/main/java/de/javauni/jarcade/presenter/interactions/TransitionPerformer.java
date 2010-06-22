package de.javauni.jarcade.presenter.interactions;

public interface TransitionPerformer<E extends Enum<E>> {
    void doTransition(E target);
    void transitToNext();
}
