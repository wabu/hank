package de.javauni.jarcade.geom;

public interface Bound extends Shape {
    Vec getLowerBound();
    Vec getUpperBound();
}
