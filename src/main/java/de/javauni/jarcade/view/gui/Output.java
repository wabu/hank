package de.javauni.jarcade.view.gui;

import java.awt.Graphics;

public interface Output {
	void repaint();
	Graphics getGhostGraphics();
	void clear();
}
