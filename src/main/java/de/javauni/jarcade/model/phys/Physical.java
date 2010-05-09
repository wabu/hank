package de.javauni.jarcade.model.phys;

import org.jbox2d.dynamics.World;

public interface Physical {
    void addTo(World w);
    void removeFrom(World w);
}
