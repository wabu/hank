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
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.javauni.jarcade.control.controlmanagement.ControlDataIsCorruptExeption;
import de.javauni.jarcade.control.controlmanagement.ControlManagement;


import de.javauni.jarcade.model.main.MainModelAccess;
import de.javauni.jarcade.model.main.MainState;
import de.javauni.jarcade.model.scene.SceneModelAccess;
import de.javauni.jarcade.model.scene.SceneModelExport;
import de.javauni.jarcade.model.scene.ScenePhase;

import de.javauni.jarcade.view.MainView;

/**
 * t3h main class
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) 
            throws InterruptedException, IllegalStateException, IOException, ControlDataIsCorruptExeption {
        Injector inj = Guice.createInjector(new HankModule());
        MainModelAccess yma = inj.getInstance(MainModelAccess.class);
        inj.getInstance(MainView.class);

        ControlManagement cm = inj.getInstance(ControlManagement.class);
        cm.load();

        yma.setState(MainState.Menu);
        Thread.sleep(100);
        yma.setState(MainState.Game);
        Thread.sleep(100);
        yma.setState(MainState.Level);
        Thread.sleep(100);

        SceneModelAccess la = inj.getInstance(SceneModelAccess.class);
        SceneModelExport le = inj.getInstance(SceneModelExport.class);
        log.debug("controlling level "+la);
        la.initialize("");

        while(le.getState() == ScenePhase.loading) {
            Thread.sleep(200);
        }
        la.setState(ScenePhase.running);
    }
}
