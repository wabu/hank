/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.Provider;

/**
 *
 * @author wabu
 */
public class DynamicProiver<C> implements Provider<C> {
    private C val = null;

    public void setInstance(C val) {
        this.val = val;
    }
    public void clear() {
        val = null;
    }

    public C get() {
        return val;
    }
}
