package de.javauni.yarrish;

import com.google.inject.AbstractModule;

import de.javauni.jarcade.ModelPhysModel;
import de.javauni.jarcade.ViewRenderModule;
import de.javauni.jarcade.ModelSceneModule;
import de.javauni.jarcade.ViewGuiModule;

import de.javauni.jarcade.control.ControlModule;

public class HankModule extends AbstractModule {
    @Override
    public void configure() {
        install(new MainModelModule());
        install(new ModelSceneModule());
        install(new ModelPhysModel());
        install(new LevelModule());

        install(new ViewRenderModule());
        install(new ViewGuiModule());

        install(new ControlModule());

        install(new EntityModule());
    }
}
