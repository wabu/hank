package de.javauni.jarcade.geom;


public interface ShapeVisitor<E> {
    E visit(Bound bound);
    E visit(Circle circle);
    E visit(CompositeShape composite);
    E visit(Polygon poly);
    E visit(Rect rect);
    E visit(Shape other);
}
