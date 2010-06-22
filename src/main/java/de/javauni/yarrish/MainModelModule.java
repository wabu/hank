package de.javauni.yarrish;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.inject.AbstractModule;

import com.google.inject.name.Names;

import de.javauni.jarcade.model.MainModel;
import de.javauni.jarcade.model.impl.MainModelImpl;

import de.javauni.jarcade.utils.guice.ScopeManagerModule;

public class MainModelModule extends AbstractModule {
    @Override
    public void configure() {
        install(new ScopeManagerModule());
        bind(Executor.class).annotatedWith(Names.named("channel-broadcast-executor"))
            .toInstance(Executors.newSingleThreadExecutor());

        bind(MainModel.class).to(MainModelImpl.class);
    }
}
