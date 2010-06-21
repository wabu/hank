package de.javauni.jarcade.model.scene.impl;

import java.util.Iterator;

import de.javauni.jarcade.geom.Bound;

import de.javauni.jarcade.model.entities.Entity;
import de.javauni.jarcade.model.impl.event.Broadcastor;
import de.javauni.jarcade.model.impl.event.Channel;

import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.Viewport;
import de.javauni.jarcade.model.scene.event.SceneChangeListenerAdapter;
import de.javauni.jarcade.model.scene.event.ViewportListener;

public class WholeSceneView extends SceneChangeListenerAdapter implements Viewport {
    private final Channel<ViewportListener> chan;
    private final Scene scene;

    public WholeSceneView(Channel<ViewportListener> chan, Scene scene) {
        this.scene = scene;
        this.scene.getSceneChannel().addListener(this);
        this.chan = chan;
    }

    @Override
    public Bound getViewport() {
        return scene.getWorldSize();
    }

    @Override
    public Channel<ViewportListener> getViewportChannel() {
        return chan;
    }

    @Override
    public Iterator<Entity> iterator() {
        return scene.getAllEntities().iterator();
    }

    @Override
    public void onEntitySpawned(final Entity e, Layer layer) {
        chan.broadcast(new Broadcastor<ViewportListener>() {
            @Override
            public void apply(ViewportListener l) {
                l.entityEntersViewport(e);
            };
        });
    }

    @Override
    public void onEntityRemoved(final Entity e, Layer layer) {
        chan.broadcast(new Broadcastor<ViewportListener>() {
            @Override
            public void apply(ViewportListener l) {
                l.entityLeavesViewport(e);
            };
        });
    }
}

