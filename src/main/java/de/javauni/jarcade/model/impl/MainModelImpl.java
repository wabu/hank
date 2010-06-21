/*
 *  Copyright (C) 2010 Daniel Waeber <wabu@inf.fu-berlin.de>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package de.javauni.jarcade.model.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.javauni.jarcade.model.impl.AbstractStateModel;
import de.javauni.jarcade.model.MainModel;
import de.javauni.jarcade.model.StateModel;

import de.javauni.jarcade.model.impl.event.Channel;
import de.javauni.jarcade.utils.guice.ScopeManager;

/**
 * implements the main game state
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@Singleton
public class MainModelImpl extends AbstractStateModel<MainModel.State> implements MainModel {
    private final ScopeManager<MainModel.State> scopes;

    @Inject MainModelImpl(
            Channel<StateModel.ChangeListener<MainModel.State>> channel,
            ScopeManager<MainModel.State> scopes) {
        super(channel, MainModel.State.Void);
        this.scopes = scopes;
    }

    @Override
    protected void doStateTransition(MainModel.State src, final MainModel.State tgt) {
        switch(tgt) {
            case Menu:
                scopes.clearOtherScopes(tgt);
                break;
            case Game:
                scopes.clearScope(MainModel.State.Level);
                scopes.activateScope(tgt);
                break;
            case Level:
                scopes.activateScope(tgt);
                break;
            default:
                throw new UnsupportedOperationException("state "+tgt+" not implemented yet");
        }
    }

    @Override
    public void setNextState() {
        switch(getState()) {
            case Void:
                setState(State.Menu);
                break;
            case Menu:
                setState(State.Game);
                break;
            case Game:
                setState(State.Level);
                break;
            case Level:
                setState(State.Game);
                break;
            default:
                throw new UnsupportedOperationException("can't set next state after "+getState());
        }
    }
}
