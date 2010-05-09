package de.javauni.jarcade.geom;


public class ShapeVisitorAdapter<E> implements ShapeVisitor<E> {

    public E visit(Bound bound) {
        return visit((Shape)bound);
    }

    public E visit(Circle cirle) {
        return visit((Shape)cirle);
    }

    public E visit(CompositeShape composite) {
        return visit((Shape)composite);
    }

    public E visit(Polygon poly) {
        return visit((Shape)poly);
    }

    public E visit(Rect rect) {
        return visit((Shape)rect);
    }

    public E visit(Shape other) {
        return null;
    }
}
