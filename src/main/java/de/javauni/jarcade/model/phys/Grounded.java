package de.javauni.jarcade.model.phys;

import org.jbox2d.dynamics.World;

import de.javauni.jarcade.geom.Shape;
import de.javauni.jarcade.model.scene.AbstractEntity;

public class Grounded extends AbstractEntity implements Physical {
    private final Shape shape;
    private org.jbox2d.collision.shapes.Shape[] ps;

    public Grounded(int id, Shape shape) {
        super(id);
        this.shape = shape;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public void addTo(final World w) {
        ps = Phys.addTo(w.getGroundBody(), shape);
    }

    public void removeFrom(World w) {
        for(org.jbox2d.collision.shapes.Shape p : ps) {
            w.getGroundBody().destroyShape(p);
        }
    }
}

