package de.javauni.jarcade.model.phys;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import javax.annotation.CheckForNull;

import org.jbox2d.collision.shapes.CircleDef;
import org.jbox2d.collision.shapes.PolygonDef;
import org.jbox2d.collision.shapes.ShapeDef;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.XForm;

import org.jbox2d.dynamics.Body;

import com.google.common.base.Function;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.javauni.jarcade.geom.Circle;
import de.javauni.jarcade.geom.CompositeShape;
import de.javauni.jarcade.geom.Polygon;
import de.javauni.jarcade.geom.Rect;
import de.javauni.jarcade.geom.Shape;
import de.javauni.jarcade.geom.ShapeVisitor;
import de.javauni.jarcade.geom.ShapeVisitorAdapter;
import de.javauni.jarcade.geom.Vec;

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
    }

    public static class TransformedShape implements Shape { 
        protected final Shape base;
        protected final Body body;

        public TransformedShape(Shape base, Body body) {
            this.base = base;
            this.body = body;
        }

        public final Vec map(Vec v) {
            return from(XForm.mul(body.getXForm(), to(v)));
        }

        public final Vec mid() {
            return map(base.mid());
        }
        public final Vec size() {
            return map(base.size());
        }
        
        public final float rotation() {
            return base.rotation() + body.getAngle();
        }

        public final Iterable<? extends Vec> getVertexes()
            throws UnsupportedOperationException {
            return Iterables.transform(
                    base.getVertexes(),
                    new Function<Vec,Vec>() {
                        public Vec apply(Vec from) {
                            return map(from);
                        };
                    });
        }

        public <E> E accept(ShapeVisitor<E> visitor) {
            return visitor.visit(this);
        }
    }

    public static class TransformedRect extends TransformedShape implements Rect { 
        public TransformedRect(Rect base, Body body) {
            super(base, body);
        }

        @Override
        public <E> E accept(ShapeVisitor<E> visitor) {
            return visitor.visit(this);
        }
    }

    public static class TransformedCircle extends TransformedShape implements Circle { 
        private final float rad;
        public TransformedCircle(Circle base, Body body) {
            super(base, body);
            rad = base.radius();
        }

        public float radius() {
            return rad;
        };

        @Override
        public <E> E accept(ShapeVisitor<E> visitor) {
            return visitor.visit(this);
        }
    }

    public static class TransformedPolygon extends TransformedShape implements Polygon { 
        public TransformedPolygon(Polygon base, Body body) {
            super(base, body);
        }

        @Override
        public <E> E accept(ShapeVisitor<E> visitor) {
            return visitor.visit(this);
        }
    }

    public static class TransformedComposite extends TransformedShape implements CompositeShape { 
        protected final CompositeShape composite;
        public TransformedComposite(CompositeShape base, Body body) {
            super(base, body);
            this.composite = base;
        }

        @Override
        public <E> E accept(ShapeVisitor<E> visitor) {
            return visitor.visit(this);
        }

        public Iterator<Shape> iterator() {
            return Iterators.transform(composite.iterator(),
                new Function<Shape,Shape>() {
                    public Shape apply(Shape from) {
                        return from(body, from);
                    };
                });
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
                for(Vec v : shape.getVertexes()) {
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
        });
    }

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

    @SuppressWarnings("unchecked")
    public static <S extends Shape> S from(final Body b, final S base) {
        return base.accept(new ShapeVisitorAdapter<S>() {
            public S visit(Circle cirle) {
                return (S)new TransformedCircle(cirle, b);
            };
            public S visit(CompositeShape composite) {
                return (S)new TransformedComposite(composite, b);
            };
            public S visit(Polygon poly) {
                return (S)new TransformedPolygon(poly, b);
            };
            public S visit(Rect rect) {
                return (S)new TransformedRect(rect, b);
            };
            public S visit(Shape other) {
                return (S)new TransformedShape(other, b);
            };
        });
    }
}
