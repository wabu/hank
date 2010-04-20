package view;

import java.awt.Graphics;

import renderer.Renderer;

public class View implements StateListener<MainState>, RenderingEngine2D{
	MainState state;
	Graphics gfx;
	@Override
	public void onStateChange(MainState state) {
		this.state=state;
	}
	@Override
	public <E> void addRenderer(Renderer<E> renderer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <E> boolean removeRenderer(Renderer<E> renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setViewPort(Viewport vp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDrawPanel(Graphics gfx) {
		this.gfx=gfx;
		
	}
	

}
