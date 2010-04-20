/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package renderer.Entity;

/**
 *
 * @author wabu
 */
public @interface ImpliedProperty {
    String name();
    String description() default "";
}
