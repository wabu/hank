package de.javauni.jarcade.geom;

public interface Polygon extends Shape {
    Iterable<? extends Vec> getVertexes();
}
