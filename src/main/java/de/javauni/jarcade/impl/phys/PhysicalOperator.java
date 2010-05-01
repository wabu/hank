package de.javauni.jarcade.impl.phys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.assistedinject.Assisted;

import net.phys2d.raw.World;

import com.google.inject.Inject;

import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.LayerChangeListener;

import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.jarcade.model.scene.operate.Operator;

public class PhysicalOperator implements Operator<Layer>, LayerChangeListener {
    private final Logger log = LoggerFactory.getLogger(PhysicalOperator.class);

    private final World physWorld;

    @Inject
    public PhysicalOperator(@Assisted Layer layer, World physWorld) {
        this.physWorld = physWorld;
        layer.getLayerChannel().addListener(this);
    }

	@Override
	public void step(Layer e, long delta) {
        physWorld.step(delta);
	}

	@Override
	public void onEntityAdded(Entity e) {
        if(e instanceof Physical) {
            Physical p = (Physical)e;
            log.debug("added physical object "+p);
            p.addTo(physWorld);
        }
	}

	@Override
	public void onEntityRemoved(Entity e) {
        if(e instanceof Physical) {
            Physical p = (Physical)e;
            log.debug("removed physical object "+p);
            p.removeFrom(physWorld);
        }
	}

}
