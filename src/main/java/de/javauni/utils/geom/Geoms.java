package de.javauni.utils.geom;

public class Geoms {
    public static Vec plus(Vec a, Vec b) {
        return new VecI(a.x() + b.x(), a.y()+b.y());
    }

    public static Vec scale(Vec c, float f) {
        return new VecI(c.x()*f, c.y()*f);
    }

    public static Vec rotate(Vec v, float angle) {
        float c = (float)Math.cos(angle);
        float s = (float)Math.sin(angle);
        return new VecI(
                c*v.x()+s*v.y(),
                c*v.y()-s*v.x());
    }
}
