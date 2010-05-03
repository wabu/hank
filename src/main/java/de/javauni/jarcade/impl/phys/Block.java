package de.javauni.jarcade.impl.phys;

import de.javauni.jarcade.impl.scene.SimpleEntity;

import de.javauni.jarcade.model.scene.entity.CollidableEntity;

import de.javauni.utils.geom.Shape;

import de.javauni.utils.props.ImpliedProperty;
import de.javauni.utils.props.Property;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;

public class Block extends SimpleEntity implements Physical, CollidableEntity {
    private final Body body;

    public Block(int id, 
            @ImpliedProperty(name="position") Shape shape,
            @Property(name="mass", value="20") float m) {
        super(id, shape);
        body = Phys.to(shape, m);
    }

    @Override
    public void addTo(World w) {
        w.add(body); 
    }

    @Override
    public void removeFrom(World w) {
        w.remove(body);
    }

    @Override
    public Shape getShape() {
        // XXX cache shape object?
        return Phys.from(body);
    }

    public String toString() {
        return body.toString();
    }
}
