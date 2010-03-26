/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;
import java.util.AbstractList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.CheckForNull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 *
 * @param <E>
 * @author wabu
 */
@ThreadSafe
public class IdList<E> extends AbstractList<E>{
    private final Object mutex = new Object();

    @GuardedBy("mutex")
    private E[] elems;

    @CheckForNull
    private E[] nexts;

    private final AtomicInteger ids = new AtomicInteger();

    public IdList() {
        this(64);
    }

    public IdList(int initSize) {
        elems = (E[])new Object[initSize];
    }

    public <S extends E> S add(Function<Integer, S> construct) {
        AddHelper<E> h = add();
        S e = construct.apply(h.getId());
        h.set(e);
        return e;
    }

    public <S extends E> AddHelper<S> add(){
        final int id = ids.getAndIncrement();
        assureSize(id);
        return new AddHelper<S>() {
            public void set(S e) {
                IdList.this.set(id, e);
            }
            public int getId() {
                return id;
            }
        };
    }

    public int getNextId() {
        return ids.getAndIncrement();
    }

    @Override
    public void add(int i, E e) {
        assureSize(i);
        set(i, e);
    }

    @Override
    public E set(int i, E e) {
        E prev = null;

        E[] es = elems;
        if(es.length > i) {
            es[i] = e;
            prev = es[i];
        }

        E[] ns = nexts;
        if(ns != null) {
            ns[i] = e;
        }

        if(es.length <= i && ns==null) {
            throw new IndexOutOfBoundsException();
        }

        return prev;
    }

    private void assureSize(int id) {
        if(elems.length < id) {
            synchronized(mutex) {
                if(elems.length < id) {
                    nexts = ObjectArrays.newArray(elems, elems.length*2);
                    System.arraycopy(elems, 0, nexts, 0, elems.length);
                    elems = nexts;
                    nexts = null; // NOPMD
                }
            }
        }
    }

    @Override
    public E get(int index) {
        Preconditions.checkElementIndex(index, elems.length);
        return elems[index];
    }

    @Override
    public int size() {
        return ids.get();
    }
}

/* * * *
 * Correctness 
 * * * *
 * thread1:
 * 1.1:  nexts = new E[...]
 * 1.2:  copy(elems, nexts)
 * 1.3:  elems = nexts
 * 1.4:  nexts = null
 * * * *
 * thread2:
 * 2.1:  es = elems
 * 2.2:  if i<es
 *          es[i]=e
 * 2.3:  ns = nexts
 * 2.4:  if ns
 *          ns[i]=e
 * * * *
 * TODO: show that id list is threadsave
 */