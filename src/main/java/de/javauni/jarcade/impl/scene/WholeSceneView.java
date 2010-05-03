package de.javauni.jarcade.impl.scene;

import java.util.Iterator;

import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.Viewport;
import de.javauni.jarcade.model.scene.ViewportListener;
import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.utils.geom.Box;
import de.javauni.utils.geom.Geoms;
import de.javauni.utils.geom.Vec;

public class WholeSceneView implements Viewport {
    private final Channel<ViewportListener> chan;
    private final Scene scene;

    public WholeSceneView(Channel<ViewportListener> chan, Scene scene) {
        this.scene = scene;
        this.chan = chan;
    }

    @Override
    public Box getView() {
        Vec vec = scene.getWorldSize();
        Vec mid = Geoms.scale(vec, 0.5f);
        return new Box(mid, vec);
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
