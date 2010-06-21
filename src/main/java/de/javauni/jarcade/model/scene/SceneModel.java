package de.javauni.jarcade.model.scene;

import de.javauni.jarcade.model.StateModel;

import de.javauni.jarcade.model.impl.event.Channel;

import de.javauni.jarcade.model.scene.event.SceneChangeListener;

public interface SceneModel extends StateModel<SceneModel.Phase> {
    enum Phase {
        loading, initialized, intro, warmup, running, paused, outro, closed;
    }

    /**
     * initializes the world by loading it from a given ressources
     * @param ressources ressources location
     * @throws IllegalStateException when already initialized
     * @throws IOException when level can't be loaded
     */
    void initialize(String name);

    /**
     * get the secen scene object of this model
     * @return scene object
     * @see Scene
     */
    Scene getScene();

    Channel<SceneChangeListener> getSceneChannel();
}
