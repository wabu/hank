package de.javauni.utils.geom;

public class Rect implements Shape {
    private final Vec mid;
    private final Vec size;
    private final float rot;

    public Rect(Vec mid, Vec size, float rot) {
        this.size = size;
        this.mid = mid;
        this.rot = rot;
    }

    public Vec mid() {
        return mid;
    }

    public Vec size() {
        return size;
    }

    public float rotation() {
        return rot;
    }

}
