package de.javauni.jarcade.presenter.impl;

import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import com.google.inject.name.Named;

import de.javauni.jarcade.model.StateModel;

import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.SceneModel;
import static de.javauni.jarcade.model.scene.SceneModel.Phase;

import de.javauni.jarcade.model.scene.event.SceneChangeListener;

import de.javauni.jarcade.presenter.Presentor;

import de.javauni.jarcade.presenter.rendering.RendererFactory;
import de.javauni.jarcade.presenter.rendering.RendererMap;

import de.javauni.jarcade.utils.UpdateLoop;

import de.javauni.jarcade.view.GraphicsContext;
import de.javauni.jarcade.view.RenderedView;

// XXX refactor out common rendering persentor code
public class SceneRenderingPresentor<G extends GraphicsContext> 
        extends UpdateLoop
        implements Presentor<SceneModel>, 
            StateModel.ChangeListener<Phase>, SceneChangeListener {
    private final Logger log = LoggerFactory.getLogger(SceneRenderingPresentor.class);

    private final RenderedView<Scene, G> view;

    private final RendererMap<G> map;
    private final RendererFactory<G> fac;
    
    // FIXME where to get gfx context
    private G gc = null;

    @Inject
    public SceneRenderingPresentor(
            RenderedView<Scene,G> view, 
            RendererMap<G> map,
            RendererFactory<G> fac,
            SceneModel scene,
            @Named("rendering-update-intervall") long intervall,
            @Named("rendering-thread-factory") ThreadFactory tf
        ) {
        super(intervall, tf);

        this.view = view;
        this.map = map;
        this.fac = fac;

        scene.getSceneChannel().addListener(this);
        scene.getStateChangeChannel().addListener(this);
    }

    public void onStateChange(Phase src, Phase tgt) {
        switch(tgt) {
            case loading:
                // TODO loadscreen??
                break;
            case initialized:
                view.bindGraphics(gc);
                this.init();
            case intro:
            case warmup:
            case running:
            case outro:
                this.start();
            case paused:
                this.pause();
            case closed:
                this.close();
                view.unbindGraphics();
        }
    };

    public void update(long delta) {
        view.render(gc, map, delta);
    }

    @Override
    public void onLayerAdded(Layer layer) {
        // nop
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

