package de.javauni.jarcade.presenter.impl;

import de.javauni.jarcade.presenter.interactions.GameActionPerformer;

import de.javauni.jarcade.utils.guice.ManagedScope;

@ManagedScope
public class GameActionPerformerImpl implements GameActionPerformer<Object> {

    // TODO game actions
    
    @Override
    public void doGameAction(int player, Object action) {
    }

    @Override
    public void doGameAction(int player, Object action, boolean param) {
    }

    @Override
    public void doGameAction(int player, Object action, float param) {
    }

}
