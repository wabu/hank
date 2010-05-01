package de.javauni.jarcade.impl.scene;

import java.util.Iterator;

import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.Viewport;
import de.javauni.jarcade.model.scene.ViewportListener;
import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.utils.geom.Box;

public class WholeSceneView implements Viewport {
    private final Channel<ViewportListener> chan;
    private final Scene scene;

    public WholeSceneView(Channel<ViewportListener> chan, Scene scene) {
        this.scene = scene;
        this.chan = chan;
    }

    @Override
    public Box getView() {
        return scene.getWorldBox();
    }

    @Override
    public Channel<ViewportListener> getViewportChannel() {
        return chan;
    }

    @Override
    public Iterator<Entity> iterator() {
        return scene.getAllEntities().iterator();
    }

}
