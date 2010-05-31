package de.javauni.jarcade.utils.guice;

import java.lang.reflect.TypeVariable;

import java.util.Map.Entry;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

public abstract class GuiceBindingHelper<P extends Provider<?>> {

    public void addBindingsFrom(Injector inj) {
        TypeVariable<?> tv = getClass().getTypeParameters()[0];

        for (Entry<Key<?>, Binding<?>> e : inj.getBindings().entrySet()) {
            TypeLiteral foo = e.getKey().getTypeLiteral();
        }
    }

    abstract void apply(P provider);
}
