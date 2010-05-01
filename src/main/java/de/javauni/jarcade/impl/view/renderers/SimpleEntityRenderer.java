package de.javauni.jarcade.impl.view.renderers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

import de.javauni.jarcade.view.render.Renderer;
import de.javauni.jarcade.impl.scene.SimpleEntity;

import java.awt.Color;
import java.awt.Graphics2D;

import de.javauni.utils.geom.Box;

@Singleton
public class SimpleEntityRenderer implements Renderer<SimpleEntity> {
    private final Logger log = LoggerFactory.getLogger(SimpleEntityRenderer.class);

	@Override
	public void render(SimpleEntity entity, Graphics2D gfx, long timeDelta, long levelTime) {
        if(log.isTraceEnabled()) {
            log.trace("rendering "+entity);
        }

		Box box = entity.getPositionBox();
		gfx.setColor(Color.black);
		gfx.fillRect((int) box.getX(), (int) box.getY(), (int) box.getH(), (int) box.getW());
	}
}
