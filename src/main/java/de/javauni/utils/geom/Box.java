package de.javauni.utils.geom;

public class Box implements Shape {
    private final Vec size;
    private final Vec mid;

    public Box(Vec mid, Vec size) {
        this.size = size;
        this.mid = mid;
    }

    public Box(float x, float y, float w, float h) {
        this(Geoms.plus(new VecI(x,y), new VecI(w*.5f,h*.5f)), 
                new VecI(w,h));
    }

    public Vec mid() {
        return mid;
    }

    public Vec size() {
        return size;
    }

    public float rotation() {
        return 0;
    }
}

