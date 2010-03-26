/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space;

import java.util.NoSuchElementException;

/**
 *
 * @author wabu
 */
public interface LayerEdit extends Layer {
    void add(Entity e);
    void remove(Entity e) throws NoSuchElementException;
}
