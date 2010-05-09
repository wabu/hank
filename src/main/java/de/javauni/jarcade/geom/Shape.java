package de.javauni.jarcade.geom;

public interface Shape {
    /** @return middle of the shape */
    Vec mid();

    /** @return size of the shape */
    Vec size();

    /** @return rotation */
    float rotation();

    /**
     * call visitor for shapes interface
     * @param visitor
     * @return return value of visited method
     */
    <E> E accept(ShapeVisitor<E> visitor);
}
