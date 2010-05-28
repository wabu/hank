package de.javauni.jarcade.geom.immutable;

import de.javauni.jarcade.geom.Rect;
import de.javauni.jarcade.geom.ShapeVisitor;
import de.javauni.jarcade.geom.Vec;

import de.javauni.jarcade.utils.ArrayIterable;

public final class RectI implements Rect {
    private final VecI[] corners;
    private final VecI mid;
    private final VecI size;

    public RectI(float x, float y, float w, float h) {
        this.corners = new VecI[4];
        corners[0] = new VecI(x  ,y);
        corners[1] = new VecI(x+w,y);
        corners[2] = new VecI(x+w,y+h);
        corners[3] = new VecI(x  ,y+h);
        mid = new VecI(x+w/2f, y+h/2f);
        size = new VecI(w,h);
    }

	@Override
	public <E> E accept(ShapeVisitor<E> visitor) {
		return visitor.visit(this);
	}

	@Override
	public Iterable<? extends Vec> getVertexes() throws UnsupportedOperationException {
        return new ArrayIterable<Vec>(corners);
	}

	@Override
	public Vec mid() {
		return mid;
	}

	@Override
	public float rotation() {
		return 0;
	}

	@Override
	public Vec size() {
		return size;
	}

    @Override
    public String toString() {
        return "rect@"+mid()+"+"+size();
    };
}

