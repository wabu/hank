package de.javauni.jarcade.model.scene.operate;

import com.google.inject.Inject;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.SceneChangeListener;
import de.javauni.jarcade.model.scene.SceneEdit;
import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.utils.UpdateLoop;

import java.util.concurrent.ThreadFactory;

/**
 * @author wabu
 */
public class SceneUpdateLoop extends UpdateLoop implements SceneChangeListener {
    private final SceneEdit scene;

    /**
     * @param intervall aspired time intervall in ms between the calls
     * @param tf        factory used to create a thread
     * @param scene
     */
    @Inject
    public SceneUpdateLoop(int intervall, ThreadFactory tf, SceneEdit scene) {
        super(intervall, tf);
        this.scene = scene;
        this.scene.getSceneChannel().addListener(this);
    }

    public <E> void registerOperator(Operator<E> op) {
    }

    @Override
    protected void update(long delta) {
    }

    @Override
    public void onEntitySpawned(Entity e, Layer layer) {
    }

    @Override
    public void onEntityRemoved(Entity e, Layer layer) {
    }

    @Override
    public void onEntityLayerChange(Entity e, Layer before, Layer layer) {
    }
}

