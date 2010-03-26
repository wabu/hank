/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model.main;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.exceptions.IllegalAction;
import de.javauni.jarcade.impl.model.AbstractStateModel;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.utils.guice.ScopeManager;
import de.javauni.yarrish.model.menu.MainMenuExport;

/**
 * implements the main game state
 * @author wabu
 */
@Singleton
public class MainModelImpl extends AbstractStateModel<MainState>
        implements MainModelAccess, MainModelExport{
    private final ScopeManager<MainState> scopes;
    private final Provider<MainMenuExport> mmp;

    @Inject MainModelImpl(
            Channel<StateListener<MainState>> channel,
            ScopeManager<MainState> scopes,
            Provider<MainMenuExport> mmp) {
        super(channel);
        this.scopes = scopes;
        this.mmp = mmp;
    }

    @Override
    protected void doStateTransition(MainState src, final MainState tgt) throws IllegalAction {
        switch(tgt) {
            case Menu:
                scopes.clearOtherScopes(tgt);
                mmp.get();
                break;
            case Game:
                scopes.clearScope(MainState.Level);
                scopes.activateScope(tgt);
                break;
            case Level:
                scopes.activateScope(tgt);
                break;
            default:
                throw new UnsupportedOperationException("menu "+tgt+" not implemented yet");
        }
    }
}
