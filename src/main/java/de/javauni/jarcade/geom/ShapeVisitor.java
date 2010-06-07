package de.javauni.jarcade.geom;

import javax.annotation.Nullable;

public interface ShapeVisitor<E> {
    @Nullable E visit(Bound bound);
    @Nullable E visit(Circle circle);
    @Nullable E visit(CompositeShape composite);
    @Nullable E visit(Polygon poly);
    @Nullable E visit(Rect rect);
    @Nullable E visit(Shape other);
}
