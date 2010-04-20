package de.javauni.jarcade.view;

import de.javauni.jarcade.model.space.Entity;

public interface RenderingFactory {
    <E extends Entity> Renderer<? super E> getRenderer(E entity);
}

