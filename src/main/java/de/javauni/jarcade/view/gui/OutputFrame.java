package de.javauni.jarcade.view.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.javauni.jarcade.geom.Bound;

import de.javauni.jarcade.geom.immutable.BoundI;
import de.javauni.jarcade.model.StateListener;
import de.javauni.jarcade.model.main.MainModelExport;
import de.javauni.jarcade.model.main.MainState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("serial")
public class OutputFrame extends ConstructorFrame implements Output, StateListener<MainState> {

    private final Image img_ghost;
    private final Graphics2D g_ghost;
    private final OutputPanel panel;
    private final Logger logger = LoggerFactory.getLogger(OutputFrame.class);
    @Inject
    OutputFrame(@Named("win-name") String fenstername,
               @Named("win-x") Integer x,
               @Named("win-y") Integer y,
               @Named("win-width") Integer width,
               @Named("win-height") Integer height,
               KeyboardInput kbIn, MainModelExport model) {
        super(fenstername, x, y, width, height);
        model.getStateChannel().addListener(this);
        
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

    @Override
    public void onStateChange(MainState state) {
        for(;;){
            logger.debug(state.toString());
        }
    }
}
