package de.javauni.jarcade.model;

/**
 * object with a managed life circle
 * @author wabu
 */
public interface Managed {
    void start();

    void suspend();

    void resume();

    void dellocate();
}
