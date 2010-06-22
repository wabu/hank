package de.javauni.jarcade.view.impl;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import com.google.inject.Singleton;

import de.javauni.jarcade.view.GraphicsOutput;

import de.javauni.jarcade.view.impl.JavaGraphicsContext;

@Singleton
public class JavaGraphicsOutput implements GraphicsOutput<JavaGraphicsContext> {
    private final JFrame win;

    private JavaGraphicsContext currentGC;

    public JavaGraphicsOutput() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        win = new JFrame("jarcade", gd.getDefaultConfiguration());
        win.setSize(800, 600);
        //win.setUndecorated(true);
        //win.setResizable(false);
        //gd.setFullScreenWindow(win);
        win.setVisible(true);
        win.createBufferStrategy(2);
        win.validate();
    }

    protected JavaGraphicsContext newGC() {
        currentGC = new JavaGraphicsContext(win, win.getBufferStrategy());
        return currentGC;
    };

    @Override
    public JavaGraphicsContext getCleanGraphicsContext() {
        return newGC();
    }

    @Override
    public JavaGraphicsContext getCurrentGraphicsContext() {
        return currentGC;
    }

}
