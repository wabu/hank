package de.javauni.jarcade.view.impl;

import java.awt.Graphics2D;
import java.awt.Panel;

import java.awt.image.BufferStrategy;

import de.javauni.jarcade.geom.Bound;

import de.javauni.jarcade.geom.immutable.BoundI;

import de.javauni.jarcade.view.GraphicsContext;

public class JavaGraphicsContext implements GraphicsContext {
    private final Panel panel;
    private final BufferStrategy buffer;

    public JavaGraphicsContext(Panel panel, BufferStrategy buffer) {
        this.panel = panel;
        this.buffer = buffer;
    }

    public Panel getPanel(){
        return this.panel;
    }


    public BufferStrategy getBuffer(){
        return this.buffer;
    }

    public Bound getBound() {
        return new BoundI(0, 0,
                panel.getWidth(), panel.getHeight());
    }

    public Graphics2D getGraphics() {
        return (Graphics2D)buffer.getDrawGraphics();
    }

    public void swapBuffer() {
        buffer.show();
    }
}
