package de.javauni.jarcade.view.render;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

import de.javauni.jarcade.geom.Vec;
import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.presenter.rendering.Renderer;

import java.awt.Color;
import java.awt.Graphics2D;

import de.javauni.jarcade.view.impl.JavaGraphicsContext;

@Singleton
public class GeneralEntityRenderer implements Renderer<JavaGraphicsContext, Entity> {
    private final Logger log = LoggerFactory.getLogger(GeneralEntityRenderer.class);

	@Override
	public void render(Entity entity, JavaGraphicsContext gc, long timeDelta) {
        Graphics2D gfx = gc.getGraphics();
        if(log.isTraceEnabled()) {
            log.trace("rendering "+entity);
        }

        Vec pos = entity.getShape().mid();
        Vec size = entity.getShape().size();

        AffineTransform tr = gfx.getTransform();

        gfx.translate(pos.x(), pos.y());
        gfx.rotate(entity.getShape().rotation());

		gfx.setColor(Color.black);
        gfx.fill(new Rectangle2D.Float(-size.x()/2f, -size.y()/2f, size.x(), size.y()));

        gfx.setTransform(tr);
	}
}
