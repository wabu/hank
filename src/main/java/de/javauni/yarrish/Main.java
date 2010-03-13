/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.yarrish.control.MainControl;
import de.javauni.yarrish.model.YarrishModule;

/**
 *
 * @author wabu
 */
final public class Main {
    private Main() {
    }

    public static void main(String[] args) throws InterruptedException{
        Injector inj = Guice.createInjector(new YarrishModule());

        MainControl main = inj.getInstance(MainControl.class);
        main.start();
    }
}
