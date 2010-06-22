package de.javauni.jarcade.view;

import de.javauni.jarcade.presenter.rendering.RendererMap;

public interface RenderedView<S, G extends GraphicsContext> extends View<S, G> {
    void render(G gfx, RendererMap<G> map, long delta);
}
