package de.javauni.jarcade.view.render;

import java.awt.Graphics;

public interface Output {
	void repaint();
	Graphics getGhostGraphics();
	void clear();
}
