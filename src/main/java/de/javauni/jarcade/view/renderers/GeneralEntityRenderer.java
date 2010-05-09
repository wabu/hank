package de.javauni.jarcade.view.renderers;

import java.awt.geom.AffineTransform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

import de.javauni.jarcade.geom.Vec;
import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.view.render.Renderer;

import java.awt.Color;
import java.awt.Graphics2D;

@Singleton
public class GeneralEntityRenderer implements Renderer<Entity> {
    private final Logger log = LoggerFactory.getLogger(GeneralEntityRenderer.class);

	@Override
	public void render(Entity entity, Graphics2D gfx, long timeDelta) {
        if(log.isTraceEnabled()) {
            log.trace("rendering "+entity);
        }

        Vec pos = entity.getShape().mid();
        Vec size = entity.getShape().size();

        AffineTransform tr = gfx.getTransform();

        gfx.translate(pos.x(), pos.y());
        gfx.rotate(entity.getShape().rotation());

		gfx.setColor(Color.black);
        gfx.fillRect((int)-size.x()/2, (int)-size.y()/2, (int)size.x(), (int)size.y());

        gfx.setTransform(tr);
	}
}
