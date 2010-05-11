package de.javauni.jarcade.model.phys;

import org.jbox2d.collision.shapes.ShapeDef;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import de.javauni.jarcade.geom.Shape;
import de.javauni.jarcade.model.entities.AbstractEntity;

public class SimpleDynamicBody extends AbstractEntity implements Physical {
    private Shape shape;
    private Body body;

    public SimpleDynamicBody(int id, Shape shape) {
        super(id);
        this.shape = shape;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public void addTo(final World w) {
        ShapeDef def = Phys.to(shape);
        BodyDef bd = new BodyDef();
        def.density = 1.0f;

        body = w.createBody(bd);
        org.jbox2d.collision.shapes.Shape phys 
            = body.createShape(def);
        body.setMassFromShapes();

        this.shape = Phys.from(body, phys);
    }

    public void removeFrom(World w) {
        w.destroyBody(body);
    }

    public String toString() {
        return shape.toString();
    };
}

