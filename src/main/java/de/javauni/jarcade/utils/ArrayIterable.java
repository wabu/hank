package de.javauni.jarcade.utils;

import java.util.Iterator;

import com.google.common.collect.AbstractIterator;

public class ArrayIterable<E> implements Iterable<E> {
    private final E[] ary;

    public ArrayIterable(E[] ary) {
        this.ary = ary;
    }

    public Iterator<E> iterator() {
        return new AbstractIterator<E>(){
            private int pos = 0;
            protected E computeNext() {
                if(pos == ary.length) {
                    return endOfData();
                }
                return ary[pos++];
            };
        };
    }

}
