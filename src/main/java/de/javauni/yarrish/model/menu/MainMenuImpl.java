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

package de.javauni.yarrish.model.menu;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.impl.model.AbstractMenuModel;
import de.javauni.jarcade.model.menu.MenuItem;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.utils.guice.ManagedScope;
import de.javauni.yarrish.model.main.MainModelAccess;
import de.javauni.yarrish.model.main.MainState;
import java.util.List;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@ManagedScope
class MainMenuImpl extends AbstractMenuModel<MainMenuState>
    implements MainMenuAccess, MainMenuExport {

    // TODO implemnen a menu

    @Inject
    MainMenuImpl(Channel<StateListener<MainMenuState>> channel,
                final MainModelAccess main) {
        super(channel,
                ImmutableMap.<MainMenuState, List<? extends MenuItem>>builder()
                .put(MainMenuState.Main,
                    Lists.newArrayList(new MenuItem() {
                        public void submit() {
                            main.setState(MainState.Game);
                        }
                    })
                ).build(), MainMenuState.Main);
    }



}
