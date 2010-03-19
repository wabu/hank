/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.javauni.jarcade.exceptions.IllegalAction;
import de.javauni.jarcade.model.MenuModel;
import de.javauni.jarcade.model.state.AbstractStateModelImpl;
import de.javauni.utils.guice.InstanceScopeManager;
import de.javauni.utils.guice.ManagedScope;
import de.javauni.yarrish.model.states.ModelState;

/**
 *
 * @author wabu
 */
@ManagedScope
public class YarrishModel extends AbstractStateModelImpl<ModelState> {
    private final InstanceScopeManager instanceManager;

    private final Provider<MenuModel> mmp;

    @Inject
    public YarrishModel(InstanceScopeManager instanceManager,
            Provider<MenuModel> mmp) {
        this.instanceManager = instanceManager;
        this.mmp = mmp;
    }


    @Override
    protected void doStateTransition(ModelState src, ModelState tgt) throws IllegalAction {
        switch(tgt) {
            case Menu:
                mmp.get();
                instanceManager.invalidate(YarrishLevel.class);
                instanceManager.invalidate(YarrishMap.class);
                break;
            case Map:
                break;
            case Level:
                break;
            default:
                throw new IllegalAction("State "+tgt+" not supported yet");
        }
        broadcastState(tgt);
    }
}
