/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wabu
 */
public class EnumeratedProviderImpl<I, E extends Enum<E>>
        implements EnumeratedProvider<I, E> {
    private final E[] types;
    private final Class<? extends I> iface;
    private final Map<E, Provider<? extends I>> map;

    public EnumeratedProviderImpl(Class<? extends I> iface, E ... types) {
        this.types = types;
        this.iface = iface;
        this.map = new HashMap<E, Provider<? extends I>>();
    }

    @Inject
    protected void init(Injector inj) {
        for(E e : types) {
            Provider<? extends I> p =
                    inj.getProvider(Key.get(iface, Names.named(e.name())));
            map.put(e, p);
        }
    }


    @SuppressWarnings("unchecked")
    public <O extends I> O get(E type) {
        return (O) map.get(type).get();
    }

}
