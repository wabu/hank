package de.javauni.jarcade.renderer.factory;

import com.google.inject.Injector;
import com.google.inject.Key;

import com.google.inject.util.Types;

import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.view.Renderer;


public class RendererFactoryImpl implements RendererFactory {
    private Injector inj;

    void initialize(Injector inj) {
        this.inj = inj;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends Entity> Renderer<? super E> getRenderer(E entity) {
        Class<?> klass = entity.getClass();
        return (Renderer<? super E>)inj.getInstance(Key.get(Types.newParameterizedType(Renderer.class,klass)));
    }
}
