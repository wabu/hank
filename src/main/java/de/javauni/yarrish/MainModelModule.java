package de.javauni.yarrish;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.inject.AbstractModule;

import com.google.inject.name.Names;

import de.javauni.jarcade.model.main.MainModelAccess;
import de.javauni.jarcade.model.main.MainModelExport;
import de.javauni.jarcade.model.main.MainModelImpl;

import de.javauni.jarcade.utils.guice.ScopeManagerModule;

public class MainModelModule extends AbstractModule {
    @Override
    public void configure() {
        install(new ScopeManagerModule());
        bind(Executor.class).annotatedWith(Names.named("channel-broadcast-executor"))
            .toInstance(Executors.newSingleThreadExecutor());

        bind(MainModelAccess.class).to(MainModelImpl.class);
        bind(MainModelExport.class).to(MainModelImpl.class);
    }
}
