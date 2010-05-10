package de.javauni.jarcade.model.phys;

import org.jbox2d.collision.shapes.ShapeDef;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import de.javauni.jarcade.geom.Shape;
import de.javauni.jarcade.model.scene.AbstractEntity;

public class SimpleStaticBody extends AbstractEntity implements Physical {
    private Shape shape;
    private org.jbox2d.collision.shapes.Shape phys;

    public SimpleStaticBody(int id, Shape shape) {
        super(id);
        this.shape = shape;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public void addTo(final World w) {
        ShapeDef def = Phys.to(shape);
        Body body = w.getGroundBody();
        def.friction = 1.5f;

        phys = body.createShape(def);
        shape = Phys.from(body, phys);
    }

    public void removeFrom(World w) {
        w.getGroundBody().destroyShape(phys);
    }

    public String toString() {
        return shape.toString();
    };
}

