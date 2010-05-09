package de.javauni.yarrish;

import com.google.inject.AbstractModule;


import de.javauni.jarcade.model.scene.SceneImpl;
import de.javauni.jarcade.model.scene.SceneModelImpl;
import de.javauni.yarrish.model.level.LevelModelImpl;
import de.javauni.yarrish.model.level.LevelScene;

public class LevelModule extends AbstractModule {
	@Override
	protected void configure() {
        bind(SceneImpl.class).to(LevelScene.class);
        bind(SceneModelImpl.class).to(LevelModelImpl.class);
	}
}
