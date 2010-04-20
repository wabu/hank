/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package renderer.Entity;

/**
 * an entity that has a current activity
 * @param <A> activity enum type
 * @author wabu
 */
public interface Actity<A extends Enum<A>> {
    A getCurrentActivity();
}
