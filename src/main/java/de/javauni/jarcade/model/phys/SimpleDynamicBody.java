package de.javauni.jarcade.model.phys;

import org.jbox2d.collision.shapes.ShapeDef;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import de.javauni.jarcade.geom.Shape;
import de.javauni.jarcade.model.scene.AbstractEntity;

public class SimpleDynamicBody extends AbstractEntity implements Physical {
    private Shape shape;
    private org.jbox2d.collision.shapes.Shape phys;

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
        def.density = 0.3f;

        Body body = w.createBody(bd);
        phys = body.createShape(def);
        body.setMassFromShapes();

        this.shape = Phys.from(body, phys);
    }

    public void removeFrom(World w) {
        w.getGroundBody().destroyShape(phys);
    }

    public String toString() {
        return shape.toString();
    };
}

