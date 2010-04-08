/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import de.javauni.jarcade.model.space.Layer;
import de.javauni.jarcade.model.space.logic.EntityHandler;

/**
 *
 * @author covin
 */
public interface EntityHandlerFactory {
    EntityHandler create(Layer layer);
}
