package de.javauni.jarcade.impl.phys;

import com.google.inject.assistedinject.Assisted;

import net.phys2d.raw.World;

import com.google.inject.Inject;

import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.LayerChangeListener;

import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.jarcade.model.scene.operate.Operator;

public class PhysicalOperator implements Operator<Layer>, LayerChangeListener {
    private final World physWorld;

    @Inject
    public PhysicalOperator(@Assisted Layer layer, World physWorld) {
        this.physWorld = physWorld;
        layer.getLayerChannel().addListener(this);
    }

	@Override
	public void step(Layer e, long delta) {
        physWorld.step();
	}

	@Override
	public void onEntityAdded(Entity e) {
        if(e instanceof Physical) {
            Physical p = (Physical)e;
            p.addTo(physWorld);
        }
	}

	@Override
	public void onEntityRemoved(Entity e) {
        if(e instanceof Physical) {
            Physical p = (Physical)e;
            p.removeFrom(physWorld);
        }
	}

}
