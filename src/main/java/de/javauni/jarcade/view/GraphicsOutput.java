package de.javauni.jarcade.view;

public interface GraphicsOutput<G extends GraphicsContext> {
    G getCleanGraphicsContext();
    G getCurrentGraphicsContext();
}
