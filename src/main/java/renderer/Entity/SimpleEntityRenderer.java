package renderer.Entity;

import java.awt.Graphics2D;

import renderer.Renderer;

public class SimpleEntityRenderer implements Renderer<SimpleEntity>{
	@Override
	public void render(SimpleEntity entity, Graphics2D gfx, long time) {
		Box box = entity.getPositionBox();
		gfx.fillRect((int)box.getX(), (int)box.getY(), box.getH(), box.getW());
	}	

}
