/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.utils;

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
