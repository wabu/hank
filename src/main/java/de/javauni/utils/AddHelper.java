/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils;

/**
 * an addhelper is used to add elements to an idlist
 * <pre>
 * h = ls.add();
 * e = new E(h.getId);
 * h.set(e);
 * </pre>
 * note that you can also use the add(int -> E) method in id list
 * @author wabu
 */
public interface AddHelper<E> {
    void set(E e);
    int getId();
}
