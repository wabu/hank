/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.yarrish.model.main.MainModelAccess;
import de.javauni.yarrish.model.main.MainState;
import de.javauni.yarrish.view.DummyView;

/**
 * t3h main class
 * @author wabu
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Injector inj = Guice.createInjector(new YarrishModule());
        MainModelAccess yma = inj.getInstance(MainModelAccess.class);
        inj.getInstance(DummyView.class);

        yma.setState(MainState.Menu);
        Thread.sleep(500);
        yma.setState(MainState.Game);
        Thread.sleep(500);
        yma.setState(MainState.Menu);
    }
}
