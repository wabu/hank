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

package de.javauni.yarrish;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.jarcade.model.space.SpacePhase;
import de.javauni.yarrish.model.level.LevelAccess;
import de.javauni.yarrish.model.main.MainModelAccess;
import de.javauni.yarrish.model.main.MainState;
import de.javauni.yarrish.view.DummyView;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * t3h main class
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException, IllegalStateException, IOException {
        Injector inj = Guice.createInjector(new YarrishModule());
        MainModelAccess yma = inj.getInstance(MainModelAccess.class);
        inj.getInstance(DummyView.class);

        yma.setState(MainState.Menu);
        Thread.sleep(500);
        yma.setState(MainState.Game);
        Thread.sleep(500);
        yma.setState(MainState.Level);

        Thread.sleep(500);

        LevelAccess la = inj.getInstance(LevelAccess.class);
        log.debug("controlling level "+la);
        la.initialize("");

        la.setState(SpacePhase.running);

        Thread.sleep(5000);

        System.exit(0);
    }
}
