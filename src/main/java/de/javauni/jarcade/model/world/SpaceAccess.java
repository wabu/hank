/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.world;

/**
 *
 * @author wabu
 */
public interface SpaceAccess {
    /**
     * initializes the world by loading it from a given ressources
     * @param ressources ressources location
     * @throws IllegalStateException when already initialized
     */
    void initialize(String ressources) throws IllegalStateException;

    /**
     * startes simulation of the world
     * @throws IllegalStateException if not initialized
     */
    void start() throws IllegalStateException;
    /**
     * pauses simulation of the world
     * @throws IllegalStateException if not started
     */
    void pause() throws IllegalStateException;
    /**
     * resumes simulation of the world
     * @throws IllegalStateException if not paused
     */
    void resume() throws IllegalStateException;
    /**
     * closes the world. the world itself will not be destoried, 
     * but the simluation will stop
     */
    void close();
}
