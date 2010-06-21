package de.javauni.jarcade.presenter;



public interface Interactor<P extends Performer, R> {
    void register(R subeject);
}
