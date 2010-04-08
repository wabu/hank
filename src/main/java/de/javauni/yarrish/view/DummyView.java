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

package de.javauni.yarrish.view;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.yarrish.model.level.LevelExport;
import de.javauni.yarrish.model.main.MainModelExport;
import de.javauni.yarrish.model.main.MainState;
import de.javauni.yarrish.model.menu.MainMenuExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * dummy view that shows how everything can be combined
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class DummyView implements StateListener<MainState> {
    private final Logger log = LoggerFactory.getLogger(DummyView.class);
    private final MainModelExport model;

    private final Provider<LevelDummyView> lvlProvider;

    // use constructor to get access to the model export
    // and providers, if you want to get submodles/subview when the main state changes
    @Inject
    public DummyView(MainModelExport model, Provider<LevelDummyView> lvlProvider) {
        this.model = model;
        this.lvlProvider = lvlProvider;

        model.getStateChannel().addListener(this);
    }

    public void onStateChange(MainState state) {
        assert state == model.getState();

        switch(state) {
            case Level:
                log.info("huhu level");
                LevelDummyView lvl = lvlProvider.get();
                log.debug("lvl view crated: "+lvl);

                break;
            default:
                log.error("unimplemented view {}", state);
                break;
        }
    }
}
