package de.javauni.yarrish;

import com.google.inject.AbstractModule;

import de.javauni.jarcade.ModelPhysModel;
import de.javauni.jarcade.ModelSceneModule;
import de.javauni.jarcade.PresentorModule;
import de.javauni.jarcade.PreseterRenderingModule;
import de.javauni.jarcade.ViewOutptuModule;

public class HankModule extends AbstractModule {
    @Override
    public void configure() {
        install(new MainModelModule());
        install(new ModelSceneModule());
        install(new ModelPhysModel());
        install(new LevelModule());

        install(new PresentorModule());
        install(new PreseterRenderingModule());

        install(new ViewOutptuModule());

        install(new EntityModule());
    }
}
