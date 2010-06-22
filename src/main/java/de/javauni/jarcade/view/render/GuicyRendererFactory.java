package de.javauni.jarcade.view.render;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.javauni.jarcade.model.entities.Entity;

import com.google.inject.Injector;
import com.google.inject.Key;

import com.google.inject.util.Types;

@Singleton
public class GuicyRendererFactory implements RendererFactory {
    private Injector inj;

    @Inject
    void initialize(Injector inj) {
        this.inj = inj;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends Entity> Renderer<? super E> getRenderer(E entity) {
        Class<?> klass = entity.getClass();
        return (Renderer<? super E>)inj.getInstance(
                Key.get(Types.newParameterizedType(Renderer.class, Types.supertypeOf(klass))));
    }

    @Override
    public boolean isRenderable(Entity entity) {
        return true;
    }
}
