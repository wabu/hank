package de.javauni.jarcade.presenter.impl;

import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.SceneModel;

import de.javauni.jarcade.model.scene.event.SceneChangeListener;

import de.javauni.jarcade.presenter.rendering.RendererFactory;
import de.javauni.jarcade.presenter.rendering.RendererMap;
import de.javauni.jarcade.utils.UpdateLoop;
import de.javauni.jarcade.utils.guice.ManagedScope;

import de.javauni.jarcade.view.GraphicsContext;
import de.javauni.jarcade.view.RenderedView;
import de.javauni.jarcade.view.View;

@ManagedScope
public class RenderingLoop<G extends GraphicsContext> 
    extends UpdateLoop implements View<G>, SceneChangeListener {
    public static final int defaultFPS = 60;

    private final Logger log = LoggerFactory.getLogger(RenderingLoop.class);

    private final RenderedView<G> view;
    private final RendererMap<G> map;
    private final RendererFactory<G> fac;

    private G gc = null;

    @Inject
    public RenderingLoop(
            @Named("render-intervall") long intervall,
            @Named("render-thread") ThreadFactory tf,
            RendererMap<G> map, 
            RendererFactory<G> fac, 
            RenderedView<G> view) {
        super(intervall, tf);

        this.map = map;
        this.fac =fac;
        this.view = view;
    }

    protected void update(long delta) {
        view.render(gc, map, delta);
    }

    @Override
    public void bindGraphics(G gc) {
        this.gc = gc;
        view.bindGraphics(gc);
        this.start();
    }

    @Override
    public void updateGraphics(G gc) {
        // TODO gc.getState -> pause/un
        view.updateGraphics(gc);
    }

    @Override
    public void unbindGraphics() {
        view.unbindGraphics();
        this.close();
        gc = null;
    }
    


    @Override
    public void onLayerAdded(Layer layer) {
    }

    @Override
    public void onEntitySpawned(Entity e, Layer layer) {
        log.debug("new entity {}",e);
        if (fac.isRenderable(e)) {
            map.put(e, fac.getRenderer(e));
        }
    }

    @Override
    public void onEntityRemoved(Entity e, Layer layer) {
        log.debug("entity {} removed",e);
    }

    @Override
    public void onEntityLayerChange(Entity e, Layer before, Layer layer) {
        log.debug("entity {} channged layer", e);
    }
}
