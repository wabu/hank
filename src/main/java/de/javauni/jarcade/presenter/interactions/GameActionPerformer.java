package de.javauni.jarcade.presenter.interactions;


public interface GameActionPerformer<A> extends Performer {
    void doGameAction(int player, A action);
    void doGameAction(int player, A action, boolean param);
    void doGameAction(int player, A action, float param);
}
