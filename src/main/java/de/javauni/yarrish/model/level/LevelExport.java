/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model.level;

import de.javauni.jarcade.model.space.ManagedModelExport;

/**
 *
 * @author wabu
 */
public interface LevelExport extends ManagedModelExport {
    LevelSpace getSpace();
}
