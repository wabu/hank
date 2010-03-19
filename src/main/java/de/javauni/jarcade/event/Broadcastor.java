/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.event;

/**
 *
 * @author wabu
 */
public interface Broadcastor<L extends Listener> {
    void apply(L listener);
}
