package de.javauni.jarcade.utils;

import java.util.Iterator;

import com.google.common.collect.AbstractIterator;

public class ArrayIterable<E> implements Iterable<E> {
    private final E[] ary;
    private final int length;

    public ArrayIterable(E[] ary) {
        this.ary = ary;
        this.length = ary.length;
    }
    public ArrayIterable(E[] ary, int length) {
        this.ary = ary;
        this.length = length;
    }

    public Iterator<E> iterator() {
        return new AbstractIterator<E>(){
            private int pos = 0;
            protected E computeNext() {
                if(pos == length) {
                    return endOfData();
                }
                return ary[pos++];
            };
        };
    }

}
