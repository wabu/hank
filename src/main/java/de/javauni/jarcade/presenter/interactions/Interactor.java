package de.javauni.jarcade.presenter.interactions;



public interface Interactor<P extends Performer, R> {
    void register(R subeject);
}
