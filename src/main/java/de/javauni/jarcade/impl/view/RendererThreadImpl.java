package de.javauni.jarcade.impl.view;

import java.util.concurrent.ThreadFactory;

import de.javauni.jarcade.view.render.Output;
import de.javauni.jarcade.view.render.RendererThread;
import java.awt.Graphics2D;

import de.javauni.jarcade.view.render.RendererMap;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.Viewport;
import de.javauni.jarcade.model.scene.entity.Entity;

import de.javauni.utils.guice.ManagedScope;

@ManagedScope
public class RendererThreadImpl implements RendererThread {
    public static final int defaultFPS = 60;

    private boolean running = true;
    private long fps = 60;
    private RendererMap rendererMap;
    private Output output;
    private Viewport viewport;
    private final Thread thread;

    @Inject
    public RendererThreadImpl(@Named("fps") long fps,
                        RendererMap rendererMap,
                        Output output,
                        Scene scene,
                        ThreadFactory tf) {
        if (fps == 0) {
            fps = 60;
        }
        this.rendererMap = rendererMap;
        this.output = output;
        this.viewport = scene.getViewport();
        this.fps = fps;
        this.thread = tf.newThread(this);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep((long) (1E3 / fps));
                output.clear();
                for (Entity e : viewport) {
                    rendererMap.get(e).render(e, (Graphics2D) output.getGhostGraphics(), 5, -1);
                }
                output.repaint();
            } catch (InterruptedException e) {
                return;
            }
        }
    }


    @Override
    public void start() {
        running = true;
        thread.start();
    }

    @Override
    public void stop() {
        running = false;
        thread.interrupt();
    }

    @Override
    public void setFramePerSecond(long fps) {
        if (fps == 0)
            throw new IllegalArgumentException("fps can't be zero");
        this.fps = fps;
    }

    @Override
    public long getFramePerSecond() {
        return this.fps;
    }
}
