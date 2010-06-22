package de.javauni.jarcade;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.inject.AbstractModule;

import com.google.inject.name.Names;

import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.SceneModel;
import de.javauni.jarcade.model.scene.impl.SceneImpl;
import de.javauni.jarcade.model.scene.impl.SceneModelImpl;

public class ModelSceneModule extends AbstractModule {
	@Override
	protected void configure() {
        bind(Scene.class).to(Scene.Edit.class);
        bind(Scene.Edit.class).to(SceneImpl.class);
        bind(SceneModel.class).to(SceneModelImpl.class);

        bind(ThreadFactory.class).annotatedWith(Names.named("level-update-thread"))
            .toInstance(Executors.defaultThreadFactory());
        bind(Integer.class).annotatedWith(Names.named("level-update-intervall"))
                .toInstance(25);
	}
}
