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

import com.google.inject.Key;

import de.javauni.jarcade.model.MainState;

import de.javauni.jarcade.model.scene.SceneModel;
import static de.javauni.jarcade.model.scene.SceneModel.Phase;

import de.javauni.jarcade.presenter.interactions.TransitionPerformer;

/**
 * t3h main class
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) 
            throws InterruptedException, IllegalStateException, IOException {
        Injector inj = Guice.createInjector(new HankModule());
        TransitionPerformer<MainState> m = 
            inj.getInstance(new Key<TransitionPerformer<MainState>>(){});

        //ControlManagement cm = inj.getInstance(ControlManagement.class);
        //cm.load();

        m.transitToNext();
        Thread.sleep(100);
        m.transitToNext();
        Thread.sleep(100);
        m.transitToNext();
        Thread.sleep(100);

        SceneModel sm = inj.getInstance(SceneModel.class);
        log.debug("controlling level "+sm);
        sm.initialize("");

        while(sm.getState() == Phase.loading) {
            Thread.sleep(200);
        }
        sm.setState(Phase.running);
    }
}
