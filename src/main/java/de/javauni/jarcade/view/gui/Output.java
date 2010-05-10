package de.javauni.jarcade.view.gui;

import java.awt.Graphics2D;

import de.javauni.jarcade.geom.Bound;

public interface Output {
	void repaint();
	Graphics2D getGhostGraphics();
	void clear();

    Bound getRenderBound();
}
