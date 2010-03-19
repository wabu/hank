/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.ImplementedBy;

/**
 *
 * @param <I> interface type
 * @param <E> enum type
 * @author wabu
 */
@ImplementedBy(EnumeratedProviderImpl.class)
public interface EnumeratedProvider<I, E extends Enum<E>> {
    <O extends I> O get(E type);
}
