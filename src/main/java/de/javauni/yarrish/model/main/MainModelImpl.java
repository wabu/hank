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

package de.javauni.yarrish.model.main;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.exceptions.IllegalAction;
import de.javauni.jarcade.impl.model.AbstractStateModel;
import de.javauni.jarcade.model.StateListener;
import de.javauni.utils.guice.ScopeManager;
import de.javauni.yarrish.model.menu.MainMenuExport;

/**
 * implements the main game state
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
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
