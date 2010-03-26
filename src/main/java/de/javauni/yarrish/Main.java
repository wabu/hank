/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 * @author wabu
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
        la.initialize(null);

        la.setState(SpacePhase.running);

        Thread.sleep(5000);

        System.exit(0);
    }
}
