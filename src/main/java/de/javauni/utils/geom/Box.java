/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.geom;

/**
 *
 * @author wabu
 */
public class Box extends Coord {
    private float w;
    private float h;

    public Box(float x, float y, float w, float h) {
        super(x, y);
        this.w = w;
        this.h = h;
    }
    public Box(Coord coord, float w, float h) {
        super(coord.getX(), coord.getY());
        this.w = w;
        this.h = h;
    }

    public float getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public float getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}
