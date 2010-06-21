package de.javauni.jarcade.view;

import de.javauni.jarcade.presenter.rendering.RendererMap;

public interface RenderedView<G extends GraphicsContext> extends View<G> {
    void render(G gfx, RendererMap<G> map, long delta);
}
