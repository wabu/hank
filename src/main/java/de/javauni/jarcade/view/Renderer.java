package de.javauni.jarcade.view;

import java.awt.Graphics2D;

public interface Renderer<E> {
    void render(E entity, Graphics2D gfx, long timeDelta, long levelTime);
}
