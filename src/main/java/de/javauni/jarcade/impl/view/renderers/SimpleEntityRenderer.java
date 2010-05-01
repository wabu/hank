package de.javauni.jarcade.impl.view.renderers;

import com.google.inject.Singleton;

import de.javauni.jarcade.view.render.Renderer;
import de.javauni.jarcade.impl.scene.SimpleEntity;

import java.awt.Color;
import java.awt.Graphics2D;

import de.javauni.utils.geom.Box;

@Singleton
public class SimpleEntityRenderer implements Renderer<SimpleEntity> {
	@Override
	public void render(SimpleEntity entity, Graphics2D gfx, long timeDelta, long levelTime) {
		Box box = entity.getPositionBox();
		gfx.setColor(Color.black);
		gfx.fillRect((int) box.getX(), (int) box.getY(), (int) box.getH(), (int) box.getW());
	}
}
