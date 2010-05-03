package de.javauni.jarcade.impl.phys;

import de.javauni.utils.geom.Vec;
import de.javauni.utils.geom.Shape;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

public class Phys {
    public static class PhysVec implements Vec{
        private final ROVector2f vec;

        public PhysVec(ROVector2f vec) {
            this.vec = vec;
        }

        public float x() {
            return vec.getX();
        }
        public float y() {
            return vec.getY();
        }
    }
    public static class PhysRect implements Shape { 
        private final Body body;
        private final Box box;

        public PhysRect(Body body) {
            this.body = body;
            // TODO type safty
            this.box = (Box)body.getShape();
        }

        public Vec mid() {
            return from(body.getPosition());
        }
        public Vec corner() {
            return from(box.getPoints(body.getPosition(), body.getRotation())[0]);
        }
        public Vec size() {
            return from(box.getSize());
        }
        public float rotation() {
            return body.getRotation();
        }
    }

    public static Vector2f to(Vec coord) {
        return new Vector2f(coord.x(), coord.y());
    }

    public static Body to(Shape r, float mass) {
        Box box = new Box(r.size().x(), r.size().y());
        Body body = new Body(box, mass);
        body.setPosition(r.mid().x(), r.mid().y());
        body.setRotation(r.rotation());
        return body;
    }

    public static Vec from(ROVector2f vec) {
        return new PhysVec(vec);
    }

    public static Shape from(Body b) {
        return new PhysRect(b);
    }
}
