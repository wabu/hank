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
package de.javauni.jarcade.utils;

import javax.annotation.Nullable;

public final class Pair<A, B> {

    @Nullable
    public final A fst;
    @Nullable
    public final B snd;

    public Pair(@Nullable final A fst, @Nullable final B snd) {
        this.fst = fst;
        this.snd = snd;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Pair<?, ?>) {
            final Pair<?, ?> other = (Pair<?, ?>) obj;
            return fst.equals(other.fst) && snd.equals(other.snd);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return fst.hashCode() ^ snd.hashCode();
    }

    @Override
    public String toString() {
        return "(" + fst + " " + snd + ")";
    }

    public static <A, B> Pair<A, B> get(final A a, final B b) {
        return new Pair<A, B>(a, b);
    }
}
