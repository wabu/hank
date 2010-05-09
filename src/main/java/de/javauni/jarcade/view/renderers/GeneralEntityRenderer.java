package de.javauni.jarcade.view.renderers;

import java.awt.Polygon;

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

        Polygon p = new Polygon();
        for(Vec v : entity.getShape().getVertexes()) {
            // XXX int cast are stupid, as we use a affine transform on them
            p.addPoint((int)v.x(), (int)v.y());
        }
		gfx.setColor(Color.black);
        gfx.fillPolygon(p);
	}
}
