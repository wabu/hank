/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.model.space.props;

/**
 *
 * @author wabu
 */
public @interface ImpliedProperty {
    String name();
    String description() default "";
}
