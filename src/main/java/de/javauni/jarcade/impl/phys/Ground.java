package de.javauni.jarcade.impl.phys;

import de.javauni.jarcade.impl.scene.SimpleCollidableEntity;

import de.javauni.utils.props.ImpliedProperty;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import de.javauni.utils.geom.Box;

public class Ground extends SimpleCollidableEntity implements Physical {
    private final Body phys;

    public Ground(int id, 
            @ImpliedProperty(name="position") Box pos,
            @ImpliedProperty(name="collision") Box col) {
        super(id, pos, col);

        phys = new Body(Utils.toPhys(col), Float.MIN_VALUE);
        phys.setPosition(col.getX(), col.getY());
        phys.setMoveable(false);
    }

    @Override
    public void addTo(World w) {
        w.add(phys); 
    }

    @Override
    public void removeFrom(World w) {
        w.remove(phys);
    }
}
