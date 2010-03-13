/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

/**
 *
 * @author wabu
 */
public abstract class ExtendedModule extends AbstractModule {
    @SuppressWarnings("unchecked")
    protected <T> void bindDynamic(Class<T> clazz) {
        DynamicProiver<T> p = new DynamicProiver<T>();

        bind((Key<DynamicProiver<T>>)Key.get(Types.newParameterizedType(
                DynamicProiver.class, clazz))).toInstance(p);
        bind(clazz).toProvider(p);
    }
    protected <T> void bindDynamic(TypeLiteral<T> lit) {
        DynamicProiver<T> p = new DynamicProiver<T>();

        bind((Key<DynamicProiver<T>>)Key.get(Types.newParameterizedType(
                DynamicProiver.class, lit.getType()))).toInstance(p);
        bind(lit).toProvider(p);
    }

}
