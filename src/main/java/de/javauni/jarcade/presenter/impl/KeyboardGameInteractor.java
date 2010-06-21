package de.javauni.jarcade.presenter.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;


import de.javauni.jarcade.presenter.interactions.GameActionPerformer;
import de.javauni.jarcade.presenter.interactions.Interactor;

import de.javauni.jarcade.utils.guice.ManagedScope;
import de.javauni.jarcade.view.impl.JavaGraphicsContext;

@ManagedScope
public class KeyboardGameInteractor<A extends Enum<A>> 
    implements KeyListener, Interactor<GameActionPerformer<A>, JavaGraphicsContext> {
    private static final Logger log =
        LoggerFactory.getLogger(KeyboardGameInteractor.class);

    private final GameActionPerformer<A> perfomer;

    @Inject
    public KeyboardGameInteractor(GameActionPerformer<A> perfomer) {
        this.perfomer = perfomer;
    }

    @Override
    public void register(JavaGraphicsContext gc) {
        log.debug("registered on gc");
        gc.getWindow().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        log.debug("key pressed {}", e);
        // FIXME look up keyboard map
        perfomer.doGameAction(0, null, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        log.debug("key released {}", e);
        // FIXME look up keyboard map
        perfomer.doGameAction(0, null, false);
    }

}
