package de.javauni.jarcade.view.impl;

import java.awt.Component;
import java.awt.Graphics2D;

import java.awt.image.BufferStrategy;

import de.javauni.jarcade.geom.Bound;

import de.javauni.jarcade.geom.immutable.BoundI;

import de.javauni.jarcade.view.GraphicsContext;

public class JavaGraphicsContext implements GraphicsContext {
    private final Component win;
    private final BufferStrategy buffer;
    private final Bound bound;

    private Graphics2D gfx;

    public JavaGraphicsContext(Component win, BufferStrategy buffer) {
        this.win = win;
        this.buffer = buffer;
        this.bound = new BoundI(0, 0, win.getWidth(), win.getHeight());
        this.gfx = (Graphics2D)buffer.getDrawGraphics();
    }

    // TODO use canvas+panel instead?
    public Component getWindow(){
        return this.win;
    }


    public BufferStrategy getBuffer(){
        return this.buffer;
    }

    public Bound getBound() {
        return bound;
    }


    public Graphics2D getGraphics() {
        return gfx;
    }

    public void swapBuffer() {
        gfx.dispose();
        buffer.show();
        gfx = (Graphics2D)buffer.getDrawGraphics();
    }
}
