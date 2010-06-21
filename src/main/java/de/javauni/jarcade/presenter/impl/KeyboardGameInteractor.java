package de.javauni.jarcade.presenter.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import de.javauni.jarcade.presenter.interactions.GameActionPerformer;
import de.javauni.jarcade.presenter.interactions.Interactor;
import de.javauni.jarcade.view.impl.JavaGraphicsContext;

public class KeyboardGameInteractor<A extends Enum<A>> 
    implements KeyListener, Interactor<GameActionPerformer<A>, JavaGraphicsContext> {

    private final GameActionPerformer<A> perfomer;

    public KeyboardGameInteractor(GameActionPerformer<A> perfomer) {
        this.perfomer = perfomer;
    }

    @Override
    public void register(JavaGraphicsContext gc) {
        gc.getPanel().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // FIXME look up keyboard map
        perfomer.doGameAction(0, null, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // FIXME look up keyboard map
        perfomer.doGameAction(0, null, false);
    }

}
