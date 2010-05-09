package de.javauni.jarcade.view.render;

import java.util.concurrent.ThreadFactory;

import java.awt.Graphics2D;

import com.google.inject.Inject;
import com.google.inject.name.Named;

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
        for (Entity e : viewport) {
            rendererMap.get(e).render(e, (Graphics2D) output.getGhostGraphics(), delta);
        }
        output.repaint();
    }
}
