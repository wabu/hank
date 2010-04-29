package de.javauni.jarcade.impl.phys;

import de.javauni.jarcade.impl.scene.SimpleCollidableEntity;

import de.javauni.utils.props.ImpliedProperty;
import de.javauni.utils.props.Property;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import de.javauni.utils.geom.Box;

public class Block extends SimpleCollidableEntity implements Physical {
    private final Body phys;

    public Block(int id, 
            @ImpliedProperty(name="position") Box pos,
            @ImpliedProperty(name="collision") Box col,
            @Property(name="mass", value="20") float m) {
        super(id, pos, col);

        phys = new Body(Utils.toPhys(col), m);
        phys.setPosition(col.getX(), col.getY());
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
