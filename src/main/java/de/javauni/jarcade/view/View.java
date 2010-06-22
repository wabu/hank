package de.javauni.jarcade.view;

public interface View<G extends GraphicsContext> {
    void bindGraphics(G gc);
    void updateGraphics(G gc);
    void unbindGraphics();
}

