/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import de.javauni.jarcade.event.Broadcastor;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.exceptions.IllegalAction;
import de.javauni.jarcade.model.MenuModel;
import de.javauni.jarcade.model.state.AbstractStateModelImpl;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.utils.guice.ScopeManager;
import de.javauni.yarrish.model.states.ModelState;

/**
 *
 * @author wabu
 */
@Singleton
public class YarrishModel extends AbstractStateModelImpl<ModelState> {
    private final ScopeManager<ModelState> scopes;
    private final Provider<MenuModel> mmp;

    @Inject
    public YarrishModel(
            Channel<StateListener<ModelState>> channel,
            ScopeManager<ModelState> scopes,
            Provider<MenuModel> mmp) {
        super(channel);
        this.scopes = scopes;
        this.mmp = mmp;
    }

    @Override
    protected void doStateTransition(ModelState src, final ModelState tgt) throws IllegalAction {
        switch(tgt) {
            case Menu:
                scopes.clearOtherScopes(tgt);
                mmp.get();
                break;
            case Map:
                scopes.activateScope(tgt);
                break;
            case Level:
                scopes.activateScope(tgt);
                break;
            default:
                throw new IllegalAction("State "+tgt+" not supported yet");
        }
        getStateChannel().broadcast(new Broadcastor<StateListener<ModelState>>() {
            public void apply(StateListener<ModelState> listener) {
                listener.onStateChange(tgt);
            }
        });
    }
}
