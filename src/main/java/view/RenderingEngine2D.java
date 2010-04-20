package view;

import java.awt.Graphics;

import renderer.Renderer;

public interface RenderingEngine2D {
	void setDrawPanel(Graphics c);
	void setViewPort(Viewport vp);
	<E> void addRenderer(Renderer<E> renderer);
	<E> boolean removeRenderer(Renderer<E> renderer);
	
}
