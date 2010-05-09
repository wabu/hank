package de.javauni.jarcade.model.phys;

import java.lang.Iterable;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import javax.annotation.CheckForNull;

import org.jbox2d.collision.shapes.CircleDef;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonDef;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeDef;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.XForm;

import org.jbox2d.dynamics.Body;

import com.google.common.base.Function;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterables;

import de.javauni.jarcade.geom.Bound;
import de.javauni.jarcade.geom.Circle;
import de.javauni.jarcade.geom.CompositeShape;
import de.javauni.jarcade.geom.Polygon;
import de.javauni.jarcade.geom.Rect;
import de.javauni.jarcade.geom.Shape;
import de.javauni.jarcade.geom.ShapeVisitor;
import de.javauni.jarcade.geom.ShapeVisitorAdapter;
import de.javauni.jarcade.geom.Vec;

import de.javauni.jarcade.geom.immutable.VecI;

import de.javauni.jarcade.model.phys.Phys;
import de.javauni.jarcade.model.phys.Phys;
import de.javauni.jarcade.model.phys.Phys;
import de.javauni.jarcade.model.phys.Phys;

import de.javauni.jarcade.utils.ArrayIterable;

public class Phys {
    public static class PhysVec implements Vec{
        private final Vec2 vec;

        public PhysVec(Vec2 vec) {
            this.vec = vec;
        }

        public float x() {
            return vec.x;
        }
        public float y() {
            return vec.y;
        }

        public String toString() {
            return vec.toString();
        };
    }

    protected static abstract class Transformed implements Shape, Function<Vec2, Vec> {
        final Body body;
        protected Transformed(Body body) {
            this.body = body;
        }

        public float rotation() {
            return body.getAngle();
        };

        public Vec apply(Vec2 from) {
            return from(XForm.mul(body.getMemberXForm(), from));
        };

        public String toString() {
            return "@"+mid()+"+"+size();
        };
    }

    public static class TransformedPoly extends Transformed implements Polygon { 
        private final PolygonShape shape;

        public TransformedPoly(PolygonShape shape, Body body) {
            super(body);
            this.shape = shape;
        }

        public Iterable<? extends Vec> getVertexes(){
            Iterable<Vec2> it = new ArrayIterable<Vec2>(
                    shape.getVertices(), shape.getVertexCount());
            return Iterables.transform(it, this);
        }

        public Vec mid() {
            return apply(shape.getCentroid());
        }
        public Vec size() {
            return from(shape.getOBB().extents.mul(2f));
        }

        @Override
        public <E> E accept(ShapeVisitor<E> visitor) {
            return visitor.visit(this);
        }

        public String toString() {
            return "poly"+super.toString();
        };
    }

    public static class TransformedCircle extends Transformed implements Circle { 
        private final CircleShape shape;
        public TransformedCircle(CircleShape shape, Body body) {
            super(body);
            this.shape = shape;
        }

        public Vec mid() {
            return apply(shape.getLocalPosition());
        };

        public Vec size() {
            float d = radius()*2f;
            return new VecI(d,d);
        };

        public float radius() {
            return shape.getRadius();
        };

        @Override
        public <E> E accept(ShapeVisitor<E> visitor) {
            return visitor.visit(this);
        }

        public String toString() {
            return "circle"+super.toString();
        };
    }

    public static class TransformedComposite extends Transformed implements CompositeShape { 
        public TransformedComposite(Body body) {
            super(body);
        }

        public Vec mid() {
            return from(body.getWorldCenter());
        };
        public Vec size() {
            // todo calculate size;
            return null;
        };

        @Override
        public <E> E accept(ShapeVisitor<E> visitor) {
            return visitor.visit(this);
        }

        public Iterator<Shape> iterator() {
            return new AbstractIterator<Shape>() {
                private org.jbox2d.collision.shapes.Shape shape = body.getShapeList();
                protected Shape computeNext() {
                    // XXX are shapes rly a single linked list
                    if(shape == null) {
                        return endOfData();
                    }
                    Shape trans = from(body, shape);
                    shape = shape.getNext();
                    return trans;
                };
            };
        };
    }


    public static Vec2 to(Vec coord) {
        return new Vec2(coord.x(), coord.y());
    }

    @CheckForNull
    public static ShapeDef to(final Shape shape) {
        return shape.accept(new ShapeVisitorAdapter<ShapeDef>(){
            public ShapeDef visit(Polygon poly) {
                PolygonDef pd = new PolygonDef();
                for(Vec v : poly.getVertexes()) {
                    pd.addVertex(to(v));
                }
                return pd;
            };
            public ShapeDef visit(Circle circle) {
                CircleDef cd = new CircleDef();
                cd.localPosition = to(circle.mid());
                cd.radius = circle.radius();
                return cd;
            };
            public ShapeDef visit(Rect rect) {
                Vec size = rect.size();

                PolygonDef pd = new PolygonDef();
                pd.setAsBox(size.x()/2f, size.y()/2f,
                    to(rect.mid()), rect.rotation());
                return pd;
            };
            public ShapeDef visit(Bound bound) {
                Vec size = bound.size();

                PolygonDef pd = new PolygonDef();
                pd.setAsBox(size.x()/2f, size.y()/2f,
                    to(bound.mid()), bound.rotation());
                return pd;
            };
        });
    }

    @Deprecated
    public static org.jbox2d.collision.shapes.Shape[]
            addTo(final Body bd, final Shape shape) throws IllegalArgumentException {
        return shape.accept(
                new ShapeVisitorAdapter<org.jbox2d.collision.shapes.Shape[]>(){
            public org.jbox2d.collision.shapes.Shape[] visit(CompositeShape composite) {
                LinkedList<org.jbox2d.collision.shapes.Shape> lst 
                    = new LinkedList<org.jbox2d.collision.shapes.Shape>();
                for(Shape s : composite) {
                    Collections.addAll(lst, s.accept(this));
                }
                return lst.toArray(new org.jbox2d.collision.shapes.Shape[lst.size()]);
            };
            public org.jbox2d.collision.shapes.Shape[] visit(Shape other) {
                ShapeDef def = to(other);
                if(def == null) {
                    throw new IllegalArgumentException(
                        "can't convert shapes of type "+other.getClass());
                }
                return new org.jbox2d.collision.shapes.Shape[]{bd.createShape(def)};
            };
        });
    }

    public static Vec from(Vec2 vec) {
        return new PhysVec(vec);
    }

    public static Shape from(final Body body, final org.jbox2d.collision.shapes.Shape shape) {
        switch(shape.getType()) {
        case CIRCLE_SHAPE:
            return new TransformedCircle((CircleShape)shape, body);
        case POLYGON_SHAPE:
            return new TransformedPoly((PolygonShape)shape, body);
        case SHAPE_TYPE_COUNT:
        case POINT_SHAPE:
        case EDGE_SHAPE:
        case UNKNOWN_SHAPE:
        default:
            throw new UnsupportedOperationException("can't convert a "+shape.getClass());
        }
    }

    public static Shape from(final Body b) {
        if(b.m_shapeCount == 1) {
            return from(b, b.getShapeList());
        } else {
            return new TransformedComposite(b);
        }
    }
}
