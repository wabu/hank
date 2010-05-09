package de.javauni.jarcade.geom.immutable;

import de.javauni.jarcade.geom.Vec;

public final class VecI implements Vec {
    private final float x;
    private final float y;

    public VecI(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float x() {
        return x;
    }

    @Override
    public float y() {
        return y;
    }

    public String toString() {
        return x+","+y;
    };
}
