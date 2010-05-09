package de.javauni.jarcade.model.phys;

import org.jbox2d.collision.AABB;

import org.jbox2d.common.Vec2;

import org.jbox2d.dynamics.World;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.assistedinject.Assisted;

import com.google.inject.Inject;




import de.javauni.jarcade.geom.Vec;
import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.event.LayerChangeListener;
import de.javauni.jarcade.model.scene.operate.Operator;

public class PhysicalOperator implements Operator<Layer>, LayerChangeListener {
    private final Logger log = LoggerFactory.getLogger(PhysicalOperator.class);

    private final World world;

    @Inject
    public PhysicalOperator(@Assisted Layer layer) {
        layer.getLayerChannel().addListener(this);

		AABB bb = new AABB();

        Vec lb = layer.getPosition().getLowerBound();
        Vec ub = layer.getPosition().getUpperBound();
		bb.lowerBound.set(lb.x(), lb.y());
		bb.upperBound.set(ub.x(), ub.y());

        //XXX magic number
		Vec2 g = new Vec2(0.0f, 10.0f);
		boolean sleep = true;

		world = new World(bb, g, sleep);
    }

	@Override
	public void step(Layer e, long delta) {
        //XXX magic number
        world.step((float)delta/1000f, 5);
	}

	@Override
	public void onEntityAdded(Entity e) {
        if(e instanceof Physical) {
            Physical p = (Physical)e;
            log.debug("added physical object "+p);
            p.addTo(world);
        }
	}

	@Override
	public void onEntityRemoved(Entity e) {
        if(e instanceof Physical) {
            Physical p = (Physical)e;
            log.debug("removed physical object "+p);
            p.addTo(world);
        }
	}
}
