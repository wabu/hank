package de.javauni.jarcade.presenter.interactions;


public interface TransitionPerformer<E extends Enum<E>> extends Performer {
    void doTransition(E target);
    void transitToNext();
}
