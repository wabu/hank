package de.javauni.yarrish;

import com.google.inject.AbstractModule;

import de.javauni.jarcade.ViewRenderModule;
import de.javauni.jarcade.ModelSceneModule;
import de.javauni.jarcade.ViewGuiModule;

public class HankModule extends AbstractModule {
    @Override
    public void configure() {
        install(new MainModelModule());
        install(new ModelSceneModule());
        install(new LevelModule());

        install(new ViewRenderModule());
        install(new ViewGuiModule());

        install(new EntityModule());
    }
}
