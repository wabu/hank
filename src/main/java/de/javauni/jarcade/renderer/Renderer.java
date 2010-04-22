package de.javauni.jarcade.renderer;

import java.awt.Graphics2D;

public interface Renderer<E> {
	void render(E Entity, Graphics2D gfx, long time);
}
