package de.javauni.jarcade.impl.phys;

import net.phys2d.raw.World;

public interface Physical {
    void addTo(World w);
    void removeFrom(World w);
}
