package de.javauni.yarrish;

import com.google.inject.AbstractModule;


import de.javauni.jarcade.model.scene.impl.SceneImpl;
import de.javauni.jarcade.model.scene.impl.SceneModelImpl;
import de.javauni.yarrish.model.level.LevelModel;
import de.javauni.yarrish.model.level.LevelModelImpl;
import de.javauni.yarrish.model.level.LevelScene;

public class LevelModule extends AbstractModule {
	@Override
	protected void configure() {
        bind(SceneImpl.class).to(LevelScene.class);
        bind(SceneModelImpl.class).to(LevelModelImpl.class);
        bind(LevelModel.class).to(LevelModelImpl.class);
	}
}
