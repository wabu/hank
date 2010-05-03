package de.javauni.jarcade.impl.view.renderers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

import de.javauni.jarcade.view.render.Renderer;
import de.javauni.jarcade.impl.scene.SimpleEntity;

import java.awt.Color;
import java.awt.Graphics2D;

import de.javauni.utils.geom.Shape;

@Singleton
public class GeneralEntityRenderer implements Renderer<SimpleEntity> {
    private final Logger log = LoggerFactory.getLogger(GeneralEntityRenderer.class);

	@Override
	public void render(SimpleEntity entity, Graphics2D gfx, long timeDelta, long levelTime) {
        if(log.isTraceEnabled()) {
            log.trace("rendering "+entity);
        }

		Shape shape = entity.getShape();
		gfx.setColor(Color.black);
        // FIXME rotation
        // FIXME mid is not corner
		gfx.fillRect((int) shape.mid().x(), 
                     (int) shape.mid().y(), 
                     (int) shape.size().x(), 
                     (int) shape.size().y());
	}
}
