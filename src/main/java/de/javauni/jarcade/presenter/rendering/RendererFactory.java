package de.javauni.jarcade.presenter.rendering;

import javax.annotation.Nullable;

import de.javauni.jarcade.model.scene.Entity;

import de.javauni.jarcade.view.GraphicsContext;

public interface RendererFactory<G extends GraphicsContext> {
	@Nullable <E extends Entity> Renderer<G, ? super E> getRenderer(E entity);
    boolean isRenderable(Entity entity);
}
