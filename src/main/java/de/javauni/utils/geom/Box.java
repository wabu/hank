/*
 *  Copyright (C) 2010 Daniel Waeber <wabu@inf.fu-berlin.de>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package de.javauni.utils.geom;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
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
