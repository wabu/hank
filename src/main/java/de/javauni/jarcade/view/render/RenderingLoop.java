package de.javauni.jarcade.view.render;

import java.awt.geom.AffineTransform;

import java.util.concurrent.ThreadFactory;

import java.awt.Graphics2D;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.javauni.jarcade.geom.Bound;

import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.Viewport;
import de.javauni.jarcade.utils.UpdateLoop;
import de.javauni.jarcade.utils.guice.ManagedScope;
import de.javauni.jarcade.view.gui.Output;

@ManagedScope
public class RenderingLoop extends UpdateLoop {
    public static final int defaultFPS = 60;

    private RendererMap rendererMap;
    private Output output;
    private Viewport viewport;

    @Inject
    public RenderingLoop(
            @Named("render-intervall") long intervall,
            @Named("render-thread") ThreadFactory tf,
            RendererMap rendererMap,
            Output output,
            Scene scene) {
        super(intervall, tf);

        this.rendererMap = rendererMap;
        this.output = output;
        this.viewport = scene.getViewport();
    }

    protected void update(long delta) {
        output.clear();
        Bound vb = viewport.getViewport();
        Bound rb = output.getRenderBound();

        // TODO extract to helper class
        float vlx = vb.getLowerBound().x();
        float vux = vb.getUpperBound().x();
        float vly = vb.getLowerBound().y();
        float vuy = vb.getUpperBound().y();
        float vh = vux - vlx;
        float vw = vuy - vly;

        float rlx = rb.getLowerBound().x();
        float rux = rb.getUpperBound().x();
        float rly = rb.getLowerBound().y();
        float ruy = rb.getUpperBound().y();
        float rh = rux - rlx;
        float rw = ruy - rly;

        Graphics2D gfx = output.getGhostGraphics();

        AffineTransform tr = gfx.getTransform();
        // push viewport to level transformation
        gfx.translate(0, ruy);
        gfx.scale(rw/vw, -rh/vh);
        gfx.translate(0, -vly);

        for (Entity e : viewport) {
            rendererMap.get(e).render(e, gfx, delta);
        }

        gfx.setTransform(tr);
        output.repaint();
    }
}
