package de.javauni.jarcade.model;

/**
 * object with a managed life circle
 * @author wabu
 */
public interface Managed {

    /** @deprecated XXX we let guice do the initalization */
    @Deprecated
    void initialize();

    void start();

    void suspend();

    void resume();

    void dellocate();
}
