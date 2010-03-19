/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import de.javauni.yarrish.model.main.MainModelModule;
import de.javauni.yarrish.model.menu.MenuModule;
import de.javauni.yarrish.view.DummyView;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * guice module to configure the game and bind all submodules
 * @author wabu
 */
public class YarrishModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Executor.class)
                .annotatedWith(Names.named("channel-broadcast-executor"))
                .toInstance(Executors.newSingleThreadExecutor());

        install(new MainModelModule());
        install(new MenuModule());

        bind(DummyView.class);
    }

}
