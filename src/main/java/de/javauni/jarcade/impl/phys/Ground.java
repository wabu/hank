package de.javauni.jarcade.impl.phys;

import de.javauni.jarcade.impl.scene.SimpleEntity;

import de.javauni.jarcade.model.scene.entity.CollidableEntity;

import de.javauni.utils.geom.Shape;

import de.javauni.utils.props.ImpliedProperty;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;

public class Ground extends SimpleEntity implements Physical, CollidableEntity {
    private final Body body;

    public Ground(int id, 
            @ImpliedProperty(name="shape") Shape shape) {
        super(id, shape);

        body = Phys.to(shape, Float.MAX_VALUE);
        body.setMoveable(false);
    }

    @Override
    public void addTo(World w) {
        w.add(body); 
    }

    @Override
    public void removeFrom(World w) {
        w.remove(body);
    }

    public String toString() {
        return body.toString();
    }
}
