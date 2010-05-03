package de.javauni.utils.geom;

public interface Shape {
    /** @return coordinate of mid-point of the rect */
    Vec mid();

    /** @return size of the box (width and height) */
    Vec size();

    /** @return angle */
    float rotation();
}
