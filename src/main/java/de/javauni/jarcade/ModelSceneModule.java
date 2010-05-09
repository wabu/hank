package de.javauni.jarcade;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.inject.AbstractModule;

import com.google.inject.name.Names;

import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.SceneEdit;
import de.javauni.jarcade.model.scene.SceneImpl;
import de.javauni.jarcade.model.scene.SceneModelAccess;
import de.javauni.jarcade.model.scene.SceneModelExport;
import de.javauni.jarcade.model.scene.SceneModelImpl;

public class ModelSceneModule extends AbstractModule {
	@Override
	protected void configure() {
        bind(Scene.class).to(SceneEdit.class);
        bind(SceneEdit.class).to(SceneImpl.class);
        bind(SceneModelAccess.class).to(SceneModelImpl.class);
        bind(SceneModelExport.class).to(SceneModelImpl.class);

        bind(ThreadFactory.class).annotatedWith(Names.named("level-update-thread"))
            .toInstance(Executors.defaultThreadFactory());
        bind(Integer.class).annotatedWith(Names.named("level-update-intervall"))
                .toInstance(25);
	}
}
