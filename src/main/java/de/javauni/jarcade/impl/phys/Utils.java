package de.javauni.jarcade.impl.phys;

import net.phys2d.raw.shapes.Box;

public class Utils {
    static Box toPhys(de.javauni.utils.geom.Box b) {
        return new Box(b.getH(), b.getX());
    }
}
