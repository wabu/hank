package de.javauni.jarcade.view.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.javauni.jarcade.geom.Bound;

import de.javauni.jarcade.geom.immutable.BoundI;

@SuppressWarnings("serial")
public class OutputFrame extends ConstructorFrame implements Output {

    private final Image img_ghost;
    private final Graphics2D g_ghost;
    private final OutputPanel panel;

    @Inject
    OutputFrame(@Named("win-name") String fenstername,
               @Named("win-x") Integer x,
               @Named("win-y") Integer y,
               @Named("win-width") Integer width,
               @Named("win-height") Integer height,
               KeyboardInput kbIn) {
        super(fenstername, x, y, width, height);
        panel = new OutputPanel(x, y, width, height);
        this.add(panel);
        this.addKeyListener(kbIn);
        img_ghost = panel.createImage(width, height);
        g_ghost = (Graphics2D)img_ghost.getGraphics();
    }

    @Override
    public Bound getRenderBound() {
        return new BoundI(0, 0, panel.getWidth(), panel.getHeight());
    }

    @Override
    /**
     * @return gibt das Grafikobjekt zurueck auf dem 
     * von aussen gemalt werden kann
     */
    public Graphics2D getGhostGraphics() {
        return g_ghost;
    }

    @Override
    public void repaint() {
        paint(getGraphics());
        super.repaint();
    }

    @Override
    /**
     * ueberschreibt die Paint-Methode, noetig fuer Double-buffering
     */
    public void paint(final Graphics g) {
        update(g);
    }

    @Override
    /**
     * ueberschreibt die Update-Methode, noetig fuer Double-buffering
     */
    public void update(final Graphics g) {
        g.drawImage(img_ghost, 0, 0, this);
    }

    @Override
    public void clear() {
        g_ghost.clearRect(0, 0, getWidth(), getHeight());
    }
}
