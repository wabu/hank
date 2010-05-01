package de.javauni.jarcade.impl.view;

import com.google.inject.Inject;
import de.javauni.jarcade.view.render.RendererFactory;
import com.google.inject.Injector;
import com.google.inject.Key;

import com.google.inject.util.Types;

import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.jarcade.view.render.Renderer;

import de.javauni.utils.guice.ManagedScope;

@ManagedScope
public class RendererFactoryImpl implements RendererFactory {
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
