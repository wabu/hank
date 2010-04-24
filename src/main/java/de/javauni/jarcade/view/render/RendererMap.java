package de.javauni.jarcade.view.render;

import de.javauni.jarcade.model.scene.entity.Entity;
import javax.annotation.Nullable;

/**
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public interface RendererMap {
    <E> void put(Entity Entity, Renderer<? super E> renderer);
    @Nullable <E> Renderer<? super E> get(Entity Entity);
}
