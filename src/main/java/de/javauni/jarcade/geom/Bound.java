package de.javauni.jarcade.geom;
import de.javauni.jarcade.geom.Vec;

public interface Bound extends Shape {
    Vec getLowerBound();
    Vec getUpperBound();
}
