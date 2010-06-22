package de.javauni.jarcade.presenter.rendering;

import de.javauni.jarcade.view.GraphicsContext;

public interface Renderer<G extends GraphicsContext, E> {
    void render(E entity, G gfx, long delta);
}
