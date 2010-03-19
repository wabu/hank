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
    private int w;
    private int h;

    public Box(float x, float y, int w, int h) {
        super(x, y);
        this.w = w;
        this.h = h;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}
